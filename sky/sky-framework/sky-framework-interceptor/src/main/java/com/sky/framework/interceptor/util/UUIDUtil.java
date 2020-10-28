package com.sky.framework.interceptor.util;

import java.util.UUID;

/**
 * @date 2020-10-28 028 9:42
 */
public final class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
