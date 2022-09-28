package com.sky.system.api.service;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryPropertyPrefix {

    /**
     * 方法的名称
     * <p/>
     * 例：
     * <blockquote><pre>
     *
     * {@code @DictionaryPropertyPrefix(value = "applicationPrefix")}
     * public String prefix;
     *
     * public String applicationPrefix() {
     *     return "prefixValue";
     * }
     * </pre></blockquote>
     *
     * @return String
     */
    String value() default "";
}
