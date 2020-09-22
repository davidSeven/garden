package com.forest.framework.utils;

import java.util.UUID;

public final class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
