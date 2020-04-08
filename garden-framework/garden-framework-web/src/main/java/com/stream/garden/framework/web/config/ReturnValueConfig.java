package com.stream.garden.framework.web.config;

import com.stream.garden.framework.web.interceptor.FieldSerializer;
import com.stream.garden.framework.web.interceptor.HandlerFieldSerializer;
import com.stream.garden.framework.web.interceptor.PermissionHandlerMethodReturnValueHandler;
import com.stream.garden.framework.web.json.HandlerJsonViewMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.*;

@Configuration
public class ReturnValueConfig implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private HandlerFieldSerializer handlerFieldSerializer;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> unmodifiableList = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = new ArrayList<>(unmodifiableList.size());
        for (HandlerMethodReturnValueHandler returnValueHandler : unmodifiableList) {
            if (returnValueHandler instanceof RequestResponseBodyMethodProcessor) {
                list.add(new PermissionHandlerMethodReturnValueHandler(returnValueHandler, handlerFieldSerializer));
            } else {
                list.add(returnValueHandler);
            }
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
    }

    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        return new HandlerJsonViewMessageConverter();
    }

    private Map<String, FieldSerializer> fieldSerializerMap = new HashMap<>();

    {
        Set<String> handlerFieldSet = new HashSet<>();
        Set<String> sensitiveFieldSet = new HashSet<>();

        handlerFieldSet.add("data.rows.lastLoginIp");
        sensitiveFieldSet.add("data.rows.loginFailCount");

        fieldSerializerMap.put("/system/user/pageList", new FieldSerializer(handlerFieldSet, sensitiveFieldSet));
    }

    @Bean
    public HandlerFieldSerializer handlerFieldSerializer() {
        return new HandlerFieldSerializer() {
            @Override
            public FieldSerializer filterUrl(String url) {
                return fieldSerializerMap.get(url);
            }
        };
    }
}
