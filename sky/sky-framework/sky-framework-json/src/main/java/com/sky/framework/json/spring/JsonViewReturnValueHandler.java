package com.sky.framework.json.spring;

import com.sky.framework.api.context.JsonContext;
import com.sky.framework.api.context.JsonIntensify;
import com.sky.framework.api.context.RequestContext;
import com.sky.framework.json.*;
import com.sky.framework.json.config.JsonContextConfiguration;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;
import java.util.function.BiFunction;

public class JsonViewReturnValueHandler implements HandlerMethodReturnValueHandler {
    private static final Logger log = LoggerFactory.getLogger(JsonViewReturnValueHandler.class);

    private final HandlerMethodReturnValueHandler delegate;
    private final DefaultView defaultView;
    private final JsonContextConfiguration jsonContextConfiguration;

    public JsonViewReturnValueHandler(HandlerMethodReturnValueHandler delegate, DefaultView defaultView, JsonContextConfiguration jsonContextConfiguration) {
        this.delegate = delegate;
        this.defaultView = defaultView;
        this.jsonContextConfiguration = jsonContextConfiguration;
    }

    @Override
    public boolean supportsReturnType(@NonNull MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, @NonNull MethodParameter returnType, @NonNull ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest) throws Exception {
        Object val = returnValue;
        if (JsonResultRetriever.hasValue()) {
            val = JsonResultRetriever.retrieve();
            log.debug("Found [" + ((JsonView<?>) val).getValue().getClass() + "] to serialize");
        } else {
            JsonView<?> view = defaultView.getMatch(val);
            if (view != null) {
                val = view;
                log.debug("Default view found for " + val.getClass().getCanonicalName() + ", applied before serialization");
            } else {
                log.debug("No JsonView found for thread, using returned value");
            }
        }
        RequestContext context = RequestContext.getCurrentContext();
        if (null != context) {
            // 获取到url,field字段等相关信息
            JsonContext jsonContext = context.getJsonContext();
            if (null == jsonContext && null != jsonContextConfiguration) {
                jsonContext = jsonContextConfiguration.getJsonContext(context.getUri());
            }
            if (null != jsonContext) {
                // 返回值
                Match match = Match.match()
                        .exclude(jsonContext.getExcludes())
                        .include(jsonContext.getIncludes());
                Map<String, JsonIntensify> transformMap = jsonContext.getTransformMap();
                if (MapUtils.isNotEmpty(transformMap)) {
                    for (String key : transformMap.keySet()) {
                        final JsonIntensify jsonIntensify = transformMap.get(key);
                        // X,Y,Z
                        // X是对象的类型
                        // Y是这个字段的类型
                        // Z是返回值的类型
                        match.transform(key, new BiFunction<Object, Object, Object>() {
                            @Override
                            public Object apply(Object o, Object o2) {
                                JsonIntensifyConvert jsonIntensifyConvert = JsonIntensifyUtil.load(jsonIntensify);
                                if (null != jsonIntensifyConvert) {
                                    return jsonIntensifyConvert.convert(String.valueOf(o2));
                                }
                                return o2;
                            }
                        });
                    }
                }
                // 字段增强扩展接口
                match.fieldExpandInterface(new JsonIntensifyFieldExpandInterface(jsonContext));
                JsonView<Object> objectJsonView = JsonView.with(val)
                        .onClass(val.getClass(), match);
                if (val instanceof JsonView) {
                    JsonResult jsonResult = JsonResult.instance();
                    Object value = jsonResult.use(objectJsonView)
                            .returnValue();
                    delegate.handleReturnValue(value, returnType, mavContainer, webRequest);
                } else {
                    delegate.handleReturnValue(objectJsonView, returnType, mavContainer, webRequest);
                }
                return;
            }
        }
        delegate.handleReturnValue(val, returnType, mavContainer, webRequest);
    }

}