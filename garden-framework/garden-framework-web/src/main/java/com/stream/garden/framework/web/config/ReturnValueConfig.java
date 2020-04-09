package com.stream.garden.framework.web.config;

import com.stream.garden.framework.web.interceptor.FieldSerializer;
import com.stream.garden.framework.web.interceptor.HandlerFieldNameSerializer;
import com.stream.garden.framework.web.interceptor.HandlerFieldSerializer;
import com.stream.garden.framework.web.interceptor.PermissionHandlerMethodReturnValueHandler;
import com.stream.garden.framework.web.json.HandlerJsonViewMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.*;

@Configuration
public class ReturnValueConfig implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(ReturnValueConfig.class);

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private ApplicationContext applicationContext;
    // @Autowired
    private HandlerFieldSerializer handlerFieldSerializer;
    // @Autowired
    private HandlerFieldNameSerializer handlerFieldNameSerializer;
    private Map<String, FieldSerializer> fieldSerializerMap = new HashMap<>();

    {
        Set<String> handlerFieldSet = new HashSet<>();
        Set<String> sensitiveFieldSet = new HashSet<>();

        handlerFieldSet.add("data.rows.lastLoginIp");
        sensitiveFieldSet.add("data.rows.loginFailCount");

        fieldSerializerMap.put("/system/user/pageList", new FieldSerializer(handlerFieldSet, sensitiveFieldSet));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 查找实现类
        handlerFieldSerializer = getBean(HandlerFieldSerializer.class);
        handlerFieldNameSerializer = getBean(HandlerFieldNameSerializer.class);

        List<HandlerMethodReturnValueHandler> unmodifiableList = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = new ArrayList<>(unmodifiableList.size());
        for (HandlerMethodReturnValueHandler returnValueHandler : unmodifiableList) {
            if (returnValueHandler instanceof RequestResponseBodyMethodProcessor) {
                list.add(new PermissionHandlerMethodReturnValueHandler(returnValueHandler, handlerFieldSerializer, handlerFieldNameSerializer));
            } else {
                list.add(returnValueHandler);
            }
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
    }

    private <T> T getBean(Class<T> tClass) {
        Map<String, T> beanMap = this.applicationContext.getBeansOfType(tClass);
        if (null != beanMap) {
            if (beanMap.size() > 1) {
                logger.error("获取单个实现，{}存在多个实现", tClass);
            }
            return beanMap.values().iterator().next();
        }
        return null;
    }

    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        return new HandlerJsonViewMessageConverter();
    }

    @Bean
    public HandlerFieldSerializer handlerFieldSerializer() {
        // 这里后期可以做成配置化的，根据权限判断字段是否可以看见
        return new HandlerFieldSerializer() {
            @Override
            public FieldSerializer filterUrl(String url) {
                return fieldSerializerMap.get(url);
            }
        };
    }
}
