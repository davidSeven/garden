package com.forest.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @date 2020-09-23 023 10:12
 */
public class ForestAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ForestAsyncUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.error("TASK Exception message - " + throwable.getMessage());
        logger.error("Method name - " + method.getName());
        for (Object param : objects) {
            logger.error("Parameter value - " + param);
        }
    }
}
