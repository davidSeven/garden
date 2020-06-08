package com.stream.garden.framework.register;

import com.stream.garden.framework.annotation.ProxyMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public interface MethodHandler {

    Object invoke(Object[] argv) throws Throwable;

    public static class DefaultMethodHandler implements MethodHandler {
        private final MethodHandle unboundHandle;
        private MethodHandle handle;

        public DefaultMethodHandler(Method defaultMethod) {
            try {
                Class<?> declaringClass = defaultMethod.getDeclaringClass();
                Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                field.setAccessible(true);
                MethodHandles.Lookup lookup = (MethodHandles.Lookup) field.get(null);

                this.unboundHandle = lookup.unreflectSpecial(defaultMethod, declaringClass);
            } catch (NoSuchFieldException ex) {
                throw new IllegalStateException(ex);
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException(ex);
            }
        }

        /**
         * Bind this handler to a proxy object.  After bound, DefaultMethodHandler#invoke will act as if it was called
         * on the proxy object.  Must be called once and only once for a given instance of DefaultMethodHandler
         */
        public void bindTo(Object proxy) {
            if (handle != null) {
                throw new IllegalStateException("Attempted to rebind a default method handler that was already bound");
            }
            handle = unboundHandle.bindTo(proxy);
        }

        /**
         * Invoke this method.  DefaultMethodHandler#bindTo must be called before the first
         * time invoke is called.
         */
        @Override
        public Object invoke(Object[] argv) throws Throwable {
            if (handle == null) {
                throw new IllegalStateException("Default method handler invoked before proxy has been bound.");
            }
            return handle.invokeWithArguments(argv);
        }
    }

    class FactoryMethodHandler {
        private static final Logger logger = LoggerFactory.getLogger(FactoryMethodHandler.class);

        private FactoryMethodHandler() {
        }

        public static Map<String, MethodHandler> apply(Target<?> target) {
            logger.debug("初始化代理>>>");
            Map<String, MethodHandler> result = new LinkedHashMap<>();
            Class<?> targetType = target.type();
            for (Method method : targetType.getMethods()) {
                if (method.getDeclaringClass() == Object.class ||
                        (method.getModifiers() & Modifier.STATIC) != 0 ||
                        ProxyUtil.isDefault(method)) {
                    continue;
                }
                String methodName = ProxyUtil.configKey(targetType, method);
                logger.debug("methodName: {}", methodName);
                result.put(methodName, new ProxyMethodHandler(method));
            }
            return result;
        }
    }

    public static class ProxyMethodHandler implements MethodHandler {

        private final Method method;

        public ProxyMethodHandler(Method method) {
            this.method = method;
        }

        @Override
        public Object invoke(Object[] argv) throws Throwable {
            ProxyMethod proxyMethod = method.getAnnotation(ProxyMethod.class);
            if (null != proxyMethod) {
                return proxyMethod.value() + argv[0];
            }
            throw new RuntimeException("[" + method.getName() + "] is not ProxyMethod");
        }
    }
}
