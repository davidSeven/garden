package com.sky.framework.interceptor.support;

import com.sky.framework.api.context.RequestContext;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author zhangyuyuan
 * @date 2021-06-02 13:31
 */
@ControllerAdvice
@ConditionalOnClass(ResponseBodyAdvice.class)
public class HandlerResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return ResponseDto.class.equals(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (Objects.isNull(body)) {
            return null;
        }
        if (body instanceof ResponseDto) {
            // 反射设置值
            RequestContext context = RequestContext.getCurrentContext();
            if (null != context) {
                ResponseDto<?> responseDto = (ResponseDto<?>) body;
                responseDto.setRequestId(context.getRequestId());
                return responseDto;
            }
        }
        return body;
    }
}
