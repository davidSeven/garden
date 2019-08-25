package com.stream.garden.framework.web.util;

import com.stream.garden.framework.web.model.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author garden
 * @date 2019-06-22 13:49
 */
public class ContextUtil {
    private static Logger logger = LoggerFactory.getLogger(ContextUtil.class);

    private static ThreadLocal<Context> threadLocal = new ThreadLocal<>();

    public static Context getContext() {
        return threadLocal.get();
    }

    public static void setContext(Context context) {
        threadLocal.set(context);
    }

    public static boolean isLogin() {
        return null != threadLocal.get();
    }

    public static String getUserId() {
        return getContext().getUserId();
    }

    public static String getRoleId() {
        return getContext().getRoleId();
    }
}
