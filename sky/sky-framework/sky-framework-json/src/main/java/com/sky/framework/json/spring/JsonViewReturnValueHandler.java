package com.sky.framework.json.spring;

import com.sky.framework.api.context.JsonContent;
import com.sky.framework.api.context.RequestContext;
import com.sky.framework.json.JsonView;
import com.sky.framework.json.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

public class JsonViewReturnValueHandler implements HandlerMethodReturnValueHandler {
    private static final Logger log = LoggerFactory.getLogger(JsonViewReturnValueHandler.class);

    private final HandlerMethodReturnValueHandler delegate;
    private final DefaultView defaultView;

    public JsonViewReturnValueHandler(List<HttpMessageConverter<?>> converters, DefaultView defaultView) {
        this.delegate = new JsonViewResponseProcessor(converters);
        this.defaultView = defaultView;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
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
            JsonContent jsonContent = context.getJsonContent();
            if (null != jsonContent) {
                // 返回值
                JsonResult jsonResult = JsonResult.instance();
                Object value = jsonResult.use(JsonView.with(val)
                        .onClass(val.getClass(), Match.match()
                                .exclude(jsonContent.getExcludes())
                                .include(jsonContent.getIncludes())))
                        .returnValue();
                delegate.handleReturnValue(value, returnType, mavContainer, webRequest);
                return;
            }
        }
        // 获取到url,field字段等相关信息
        delegate.handleReturnValue(val, returnType, mavContainer, webRequest);
    }

}