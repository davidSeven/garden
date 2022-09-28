package com.sky.system.api.service;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryPropertyName {

    String value() default "";
}
