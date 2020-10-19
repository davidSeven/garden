package com.forest.framework.config;

import com.forest.framework.helper.BeanHelper;
import com.forest.framework.helper.JsonHelper;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @author forest
 * @date 2019-07-15 10:05
 */
public class SpringCacheKeyGenerator implements KeyGenerator {

    public static final int NO_PARAM_KEY = 0;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        char sp = ':';
        StringBuilder strBuilder = new StringBuilder(30);
        // 类名
        strBuilder.append(target.getClass().getSimpleName());
        strBuilder.append(sp);
        // 方法名
        strBuilder.append(method.getName());
        strBuilder.append(sp);
        if (params.length > 0) {
            // 参数值
            for (Object object : params) {
                if (BeanHelper.isSimpleValueType(object.getClass())) {
                    strBuilder.append(object);
                } else {
                    strBuilder.append(JsonHelper.toJson(object).hashCode());
                }
            }
        } else {
            strBuilder.append(NO_PARAM_KEY);
        }
        return strBuilder.toString();
    }
}
