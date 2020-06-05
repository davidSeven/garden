package com.stream.garden.framework.util;

import com.stream.garden.framework.api.exception.CommonException;

@SuppressWarnings("unused")
public class Asserts {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new CommonException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new CommonException(message);
        }
    }

    public static void isStartWith(String text, String prefix, String message) {
        notNull(text, message);
        if (!text.startsWith(prefix)) {
            throw new CommonException(message);
        }
    }

    public static void isEndWith(String text, String suffix, String message) {
        notNull(text, message);
        if (!text.endsWith(suffix)) {
            throw new CommonException(message);
        }
    }

}
