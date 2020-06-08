package com.stream.garden.framework.register;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Target;
import java.lang.annotation.*;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DynamicServiceRegister.class)
public @interface EnableDynamicService {

    String[] value() default {};

    Class<?>[] defaultConfiguration() default {};

}
