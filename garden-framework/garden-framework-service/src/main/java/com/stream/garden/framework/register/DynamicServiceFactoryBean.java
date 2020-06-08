package com.stream.garden.framework.register;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

/**
 *
 */
public class DynamicServiceFactoryBean implements FactoryBean<Object>, InitializingBean,
        ApplicationContextAware {

    private Class<?> type;

    private String name;

    private Class<?> fallback = void.class;

    private ApplicationContext applicationContext;

    @Override
    public Object getObject() throws Exception {
        if (!"void".equals(fallback.getName())) {
            return fallback.newInstance();
        }
        return ProxyUtil.newInstance(new Target.HardCodedTarget<>(this.type));
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getFallback() {
        return fallback;
    }

    public void setFallback(Class<?> fallback) {
        this.fallback = fallback;
    }
}
