package com.sky.system.api.service;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryPropertyConfiguration {

    /**
     * prefix
     *
     * @return prefix
     */
    String prefix() default "";
}
