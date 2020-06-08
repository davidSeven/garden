package com.stream.garden.framework.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 代理工具类
 */
public final class ProxyUtil {
    private static final Logger logger = LoggerFactory.getLogger(ProxyUtil.class);

    private ProxyUtil() {
    }

    public static <T> T newInstance(Target<T> target) {

        Map<String, MethodHandler> nameToHandler = MethodHandler.FactoryMethodHandler.apply(target);
        Map<Method, MethodHandler> methodToHandler = new LinkedHashMap<>();

        for (Method method : target.type().getMethods()) {
            if (method.getDeclaringClass() == Object.class) {
                continue;
            } else if (isDefault(method)) {
                MethodHandler.DefaultMethodHandler handler = new MethodHandler.DefaultMethodHandler(method);
                methodToHandler.put(method, handler);
            } else {
                logger.debug("ProxyUtil.newInstance.Method: {}", method.getName());
                methodToHandler.put(method, nameToHandler.get(configKey(target.type(), method)));
            }
        }

        InvocationHandler handler = new DefaultInvocationHandler(target, methodToHandler);

        @SuppressWarnings("unchecked")
        T proxy = (T) Proxy.newProxyInstance(target.type().getClassLoader(), new Class<?>[]{target.type()}, handler);

        logger.debug("proxy: {}", proxy);

        return proxy;
    }

    public static String configKey(Class<?> targetType, Method method) {
        StringBuilder builder = new StringBuilder();
        builder.append(targetType.getSimpleName());
        builder.append('#').append(method.getName()).append('(');
        for (Type param : method.getGenericParameterTypes()) {
            builder.append(getRawType(param)).append(',');
        }
        if (method.getParameterTypes().length > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.append(')').toString();
    }

    public static String getRawType(Type param) {
        return param.getTypeName();
    }

    /**
     * Identifies a method as a default instance method.
     */
    public static boolean isDefault(Method method) {
        // Default methods are public non-abstract, non-synthetic, and non-static instance methods
        // declared in an interface.
        // method.isDefault() is not sufficient for our usage as it does not check
        // for synthetic methods.  As a result, it picks up overridden methods as well as actual default methods.
        final int SYNTHETIC = 0x00001000;
        return ((method.getModifiers() & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC | SYNTHETIC)) ==
                Modifier.PUBLIC) && method.getDeclaringClass().isInterface();
    }

    /**
     * 自定义方法代理
     */
    public static class DefaultInvocationHandler implements InvocationHandler {

        private final Target<?> target;
        private final Map<Method, MethodHandler> dispatch;

        public DefaultInvocationHandler(Target<?> target, Map<Method, MethodHandler> dispatch) {
            this.target = target;
            this.dispatch = dispatch;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            switch (method.getName()) {
                case "equals":
                    try {
                        Object otherHandler = args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                        return equals(otherHandler);
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                case "hashCode":
                    return hashCode();
                case "toString":
                    return toString();
                default:
                    return dispatch.get(method).invoke(args);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultInvocationHandler) {
                DefaultInvocationHandler other = (DefaultInvocationHandler) obj;
                return target.equals(other.target);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }

        @Override
        public String toString() {
            return target.toString();
        }
    }
}

