package com.sky.framework.json.spring;

import com.sky.framework.json.JsonView;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;

import java.util.List;

public class JsonViewHttpEntityMethodProcessor extends HttpEntityMethodProcessor {

    public JsonViewHttpEntityMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  @NonNull MethodParameter returnType,
                                  @NonNull ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest) throws Exception {
        if (returnValue instanceof ResponseEntity && JsonResultRetriever.hasValue()) {
            JsonView<?> json = JsonResultRetriever.retrieve();
            ResponseEntity<?> re = (ResponseEntity<?>) returnValue;
            returnValue = ResponseEntity.status(re.getStatusCode())
                    .headers(re.getHeaders())
                    .body(json);
        }
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

}
