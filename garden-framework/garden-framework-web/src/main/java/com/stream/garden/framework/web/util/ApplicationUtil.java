package com.stream.garden.framework.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author garden
 */
@Configuration
public class ApplicationUtil implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("run ApplicationUtil.setApplicationContext");
        ApplicationUtil.applicationContext = applicationContext;
    }

    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext != null) {
            applicationContext.publishEvent(event);
        }
    }

    public static <T> T getBeans(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public static Object getBeans(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> tClass) {
        return applicationContext.getBean(name, tClass);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    public static String getProperty(String key, String defaultValue) {
        String result = defaultValue;
        Environment environment = getBeans(Environment.class);
        if (null != environment) {
            result = environment.getProperty(key, defaultValue);
        }
        return result;
    }
}
