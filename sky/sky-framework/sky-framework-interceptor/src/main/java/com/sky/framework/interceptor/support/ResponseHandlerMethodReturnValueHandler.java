package com.sky.framework.interceptor.support;

import com.sky.framework.api.context.RequestContext;
import com.sky.framework.api.dto.ResponseDto;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.InvocationTargetException;

public class ResponseHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    private final Logger logger = LoggerFactory.getLogger(ResponseHandlerMethodReturnValueHandler.class);

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(@NonNull MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, @NonNull MethodParameter methodParameter, @NonNull ModelAndViewContainer modelAndViewContainer, @NonNull NativeWebRequest nativeWebRequest) throws Exception {
        delegate.handleReturnValue(convertReturnValue(o), methodParameter, modelAndViewContainer, nativeWebRequest);
    }

    private Object convertReturnValue(Object source) {
        if (source instanceof ResponseDto) {
            // 反射设置值
            RequestContext context = RequestContext.getCurrentContext();
            if (null != context) {
                try {
                    MethodUtils.invokeMethod(source, "setRequestId", context.getRequestId());
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    logger.error("setRequestId error, " + e.getMessage(), e);
                }
            } else {
                logger.info("the context is null");
            }
        }
        return source;
    }
}
