package com.stream.garden.framework.annotation;

import java.lang.annotation.*;

/**
 * @author garden
 * @date 2020-06-08 9:11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicService {

    /**
     * 名称
     *
     * @return 名称
     */
    String value();

    Class<?>[] configuration() default {};

    Class<?> fallback() default void.class;
}
