package com.stream.garden.framework.web.util;

import com.stream.garden.framework.web.model.Context;

/**
 * @author garden
 * @date 2019-06-22 13:49
 */
public class ContextUtil {

    private static ThreadLocal<Context> threadLocal = new ThreadLocal<>();

    public static void setContext(Context context) {
        threadLocal.set(context);
    }

    public static Context getContext() {
        return threadLocal.get();
    }
}
