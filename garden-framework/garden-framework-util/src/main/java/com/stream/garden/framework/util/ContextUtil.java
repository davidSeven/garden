package com.stream.garden.framework.util;

import com.stream.garden.framework.api.model.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author garden
 * @date 2019-06-22 13:49
 */
public class ContextUtil {
    private static Logger logger = LoggerFactory.getLogger(ContextUtil.class);
    private static ThreadLocal<Context> threadLocal = new InheritableThreadLocal<>();

    private ContextUtil() {
    }

    public static Context getContext() {
        try {
            return threadLocal.get();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static void setContext(Context context) {
        threadLocal.set(context);
    }

    public static boolean isLogin() {
        return null != threadLocal.get();
    }

    public static String getUserId() {
        Context context = getContext();
        if (null != context) {
            return context.getUserId();
        }
        return null;
    }

    public static String getRoleId() {
        Context context = getContext();
        if (null != context) {
            return context.getRoleId();
        }
        return null;
    }
}
