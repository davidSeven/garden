package com.sky.framework.service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @date 2020-07-09 009 21:43
 */
public class BusinessAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(BusinessAsyncUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
        logger.error("TASK Exception message - " + throwable.getMessage());
        logger.error("Method name - " + method.getName());
        for (Object param : params) {
            logger.error("Parameter value - " + param);
        }
    }
}
