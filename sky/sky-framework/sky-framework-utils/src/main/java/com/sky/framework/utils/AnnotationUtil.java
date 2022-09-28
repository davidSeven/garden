package com.sky.framework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class AnnotationUtil {

    public static Annotation getAnnotation(Class<?> targetClass, Class<? extends Annotation> annotationClass) {
        if (null != targetClass) {
            return targetClass.getAnnotation(annotationClass);
        }
        return null;
    }

    public static Annotation getAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        if (null != method) {
            return method.getAnnotation(annotationClass);
        }
        return null;
    }

    public static Annotation getAnnotation(Field field, Class<? extends Annotation> annotationClass) {
        if (null != field) {
            return field.getAnnotation(annotationClass);
        }
        return null;
    }
}
