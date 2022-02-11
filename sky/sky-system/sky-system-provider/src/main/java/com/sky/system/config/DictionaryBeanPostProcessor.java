package com.sky.system.config;

import cn.hutool.cache.impl.LRUCache;
import com.alibaba.fastjson.JSON;
import com.sky.framework.utils.AnnotationUtil;
import com.sky.system.api.service.DictionaryProperty;
import com.sky.system.api.service.DictionaryPropertyConfiguration;
import com.sky.system.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryBeanPostProcessor implements BeanPostProcessor {

    private final DictionaryService dictionaryService;

    public DictionaryBeanPostProcessor(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        String strCapacity = this.dictionaryService.getValue("com.sky.system.dictionary.cache.capacity");
        String strTimeout = this.dictionaryService.getValue("com.sky.system.dictionary.cache.timeout");
        if (StringUtils.isNotEmpty(strCapacity)) {
            DictionaryCache.capacity = Integer.parseInt(strCapacity);
        }
        if (StringUtils.isNotEmpty(strTimeout)) {
            DictionaryCache.timeout = Long.parseLong(strTimeout);
        }
        DictionaryCache.init();
    }

    /**
     * Spring IOC容器实例化Bean
     *
     * @param bean     bean实例
     * @param beanName bean名称
     * @return 返回bean实例
     * @throws BeansException bean异常
     */
    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @Nonnull String beanName) throws BeansException {
        return bean;
    }

    /**
     * 调用bean实例的初始化方法
     *
     * @param bean     bean实例
     * @param beanName bean名称
     * @return 返回bean实例
     * @throws BeansException bean异常
     */
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        boolean accept = false;
        String prefix = "";
        if (bean instanceof DictionaryProperty) {
            accept = true;
            prefix = ((DictionaryProperty) bean).prefix();
        } else {
            Class<?> beanClass = bean.getClass();
            DictionaryPropertyConfiguration dictionaryPropertyConfiguration = (DictionaryPropertyConfiguration) AnnotationUtil.getAnnotation(beanClass, DictionaryPropertyConfiguration.class);
            if (null != dictionaryPropertyConfiguration) {
                prefix = dictionaryPropertyConfiguration.prefix();
                if (StringUtils.isNotEmpty(prefix)) {
                    accept = true;
                } else {
                    ConfigurationProperties configurationProperties = (ConfigurationProperties) AnnotationUtil.getAnnotation(beanClass, ConfigurationProperties.class);
                    if (null != configurationProperties) {
                        accept = true;
                        prefix = configurationProperties.prefix();
                        if (StringUtils.isEmpty(prefix)) {
                            prefix = configurationProperties.value();
                        }
                    }
                }
            }
        }
        if (accept) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(new DictionaryMethodInterceptor(bean, prefix, dictionaryService));
            // 返回代理bean
            return enhancer.create();
        }
        return bean;
    }

    /**
     * 数据字典属性方法拦截器
     */
    static class DictionaryMethodInterceptor implements MethodInterceptor {

        private final Object target;
        private final String prefix;
        private final DictionaryService dictionaryService;
        private final boolean cacheEnabled;

        public DictionaryMethodInterceptor(Object target, String prefix, DictionaryService dictionaryService) {
            this.target = target;
            this.prefix = prefix;
            this.dictionaryService = dictionaryService;
            String cacheEnabled = this.dictionaryService.getValue("com.sky.system.dictionary.cache.enabled");
            // true and false , on and off
            this.cacheEnabled = "true".equalsIgnoreCase(cacheEnabled) || "on".equalsIgnoreCase(cacheEnabled);
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            String methodName = method.getName();
            String propertyName;
            // getName -> name
            if (methodName.startsWith("get")) {
                propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
            } else {
                Class<?> returnType = method.getReturnType();
                // isName -> name
                if (returnType == boolean.class || returnType == Boolean.class) {
                    propertyName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
                } else {
                    propertyName = methodName;
                }
            }
            String propertyValue = null;
            if (cacheEnabled) {
                propertyValue = DictionaryCache.getCacheValue(obj.getClass().getName(), methodName);
            }
            if (null == propertyValue) {
                propertyValue = dictionaryService.getValue(prefix + "." + propertyName);
            }
            if (StringUtils.isNotEmpty(propertyValue)) {
                if (cacheEnabled) {
                    DictionaryCache.setCacheValue(obj.getClass().getName(), methodName, propertyValue);
                }
                Class<?> returnType = method.getReturnType();
                // int long boolean
                if (returnType.isPrimitive()) {
                    if (returnType == int.class) {
                        return Integer.parseInt(propertyValue);
                    } else if (returnType == long.class) {
                        return Long.parseLong(propertyValue);
                    } else if (returnType == boolean.class) {
                        return Boolean.parseBoolean(propertyValue);
                    }
                }
                // array
                // int[] long[] String[]
                else if (returnType.isArray()) {
                    if (returnType == int[].class) {
                        String[] values = propertyValue.split(",");
                        int[] intValues = new int[values.length];
                        for (int i = 0; i < values.length; i++) {
                            intValues[i] = Integer.parseInt(values[i]);
                        }
                        return intValues;
                    } else if (returnType == long[].class) {
                        String[] values = propertyValue.split(",");
                        long[] longValues = new long[values.length];
                        for (int i = 0; i < values.length; i++) {
                            longValues[i] = Long.parseLong(values[i]);
                        }
                        return longValues;
                    } else if (returnType == String[].class) {
                        return propertyValue.split(",");
                    }
                }
                // Integer
                else if (returnType == Integer.class) {
                    return Integer.valueOf(propertyValue);
                }
                // Long
                else if (returnType == Long.class) {
                    return Long.valueOf(propertyValue);
                }
                // Boolean
                else if (returnType == Boolean.class) {
                    return Boolean.valueOf(propertyValue);
                }
                // enum
                else if (returnType.isEnum()) {
                    return valueOf(returnType, propertyValue);
                }
                // String
                else if (returnType == String.class) {
                    return propertyValue;
                }
                // List
                else if (returnType == List.class) {
                    String[] values = propertyValue.split(",");
                    return new LinkedList<Object>(Arrays.asList(values));
                }
                // Map
                else if (returnType == Map.class) {
                    return JSON.parseObject(propertyValue, Map.class);
                }
            }
            return method.invoke(target, args);
        }

        @SuppressWarnings({"all"})
        private Enum<?> valueOf(Class<?> returnType, String name) {
            Class<Enum> enumType = (Class<Enum>) returnType;
            return Enum.valueOf(enumType, name);
        }
    }

    /**
     * 缓存对象
     */
    static class DictionaryCache {
        private static LRUCache<String, LRUCache<String, String>> cache;
        // default value
        static int capacity = 100;
        static long timeout = 3600000;

        public static void init() {
            cache = new LRUCache<>(capacity, timeout);
        }

        public static String getCacheValue(String classKey, String methodKey) {
            if (cache.containsKey(classKey)) {
                return cache.get(classKey).get(methodKey);
            }
            return null;
        }

        public static void setCacheValue(String classKey, String methodKey, String value) {
            if (cache.containsKey(classKey)) {
                cache.get(classKey).put(methodKey, value);
            } else {
                LRUCache<String, String> classCache = new LRUCache<>(capacity, timeout);
                classCache.put(methodKey, value);
                cache.put(classKey, classCache);
            }
        }

        public static void clearCacheValue(String classKey, String methodKey) {
            if (cache.containsKey(classKey)) {
                if (null != methodKey) {
                    cache.get(classKey).remove(methodKey);
                } else {
                    cache.remove(classKey);
                }
            }
        }

        public static void clearCacheValue(String classKey) {
            clearCacheValue(classKey, null);
        }
    }
}
