package com.sky.framework.json;

import com.sky.framework.api.context.JsonIntensify;
import com.sky.framework.json.util.ApplicationBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonIntensifyUtil {
    private final static Logger logger = LoggerFactory.getLogger(JsonIntensifyUtil.class);

    public static JsonIntensifyConvert load(JsonIntensify jsonIntensify) {
        JsonIntensifyConvert jsonIntensifyConvert = null;
        if ("class".equals(jsonIntensify.getLoadClassType())) {
            jsonIntensifyConvert = loadClassConvert(jsonIntensify.getLoadClassPath(), JsonIntensifyConvert.class);
        } else if ("spring".equals(jsonIntensify.getLoadClassType())) {
            jsonIntensifyConvert = loadSpringClassConvert(jsonIntensify.getLoadClassPath(), JsonIntensifyConvert.class);
        }
        return jsonIntensifyConvert;
    }

    public static JsonIntensifyAppend loadAppend(JsonIntensify jsonIntensify) {
        JsonIntensifyAppend jsonIntensifyAppend = null;
        if ("class".equals(jsonIntensify.getLoadClassType())) {
            jsonIntensifyAppend = loadClassConvert(jsonIntensify.getLoadClassPath(), JsonIntensifyAppend.class);
        } else if ("spring".equals(jsonIntensify.getLoadClassType())) {
            jsonIntensifyAppend = loadSpringClassConvert(jsonIntensify.getLoadClassPath(), JsonIntensifyAppend.class);
        }
        return jsonIntensifyAppend;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T loadClassConvert(String classPath, Class<T> clazz) {
        T bean = null;
        try {
            Class<T> convertClass = (Class<T>) Class.forName(classPath);
            bean = convertClass.newInstance();
        } catch (ClassNotFoundException e) {
            logger.error("转换器不存在", e);
        } catch (IllegalAccessException e) {
            logger.error("转换器访问权限不足", e);
        } catch (InstantiationException e) {
            logger.error("转换器无法实例化", e);
        }
        return bean;
    }

    public static <T> T loadSpringClassConvert(String classPath, Class<T> clazz) {
        T bean = null;
        try {
            bean = ApplicationBeanUtil.getBean(classPath, clazz);
        } catch (Exception e) {
            logger.error("获取springBean失败", e);
        }
        return bean;
    }
}
