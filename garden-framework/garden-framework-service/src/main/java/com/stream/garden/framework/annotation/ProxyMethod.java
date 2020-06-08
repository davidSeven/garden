package com.stream.garden.framework.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 代理方法注解
 */
@java.lang.annotation.Target(METHOD)
@Retention(RUNTIME)
public @interface ProxyMethod {

    String value();
}
