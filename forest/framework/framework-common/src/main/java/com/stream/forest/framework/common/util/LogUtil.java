package com.stream.forest.framework.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * JSONObjectEx
 */
@Slf4j
public abstract class LogUtil {
    /**
     * LogUtil
     */
    private LogUtil() {
    }

    /**
     * objToJSONString
     *
     * @param obj                           obj
     * @param maxLogProcSize4CollectionType maxLogProcSize4CollectionType
     * @return String
     */
    public static String objToJSONString(Object obj, int maxLogProcSize4CollectionType) {
        if (obj == null || obj instanceof ServletResponse || obj instanceof ServletRequest) {
            return null;
        }
        try {
            return JSON.toJSONString(obj, new PropertyPreFilterEx(maxLogProcSize4CollectionType), SerializerFeature.UseSingleQuotes);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * listToJSONString
     *
     * @param objAny                        objAny
     * @param maxLogProcSize4CollectionType maxLogProcSize4CollectionType
     * @return String
     */
    public static String listToJSONString(Collection<?> objAny, int maxLogProcSize4CollectionType) {
        if (objAny == null) {
            return null;
        }
        try {
            StringBuilder strb = new StringBuilder();
            for (Object obj : objAny) {
                if (strb.length() > 0) {
                    strb.append(",");
                }
                strb.append(objToJSONString(obj, maxLogProcSize4CollectionType));
            }
            return "[" + strb.toString() + "]";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * arrToJSONString
     *
     * @param objAny                        objAny
     * @param maxLogProcSize4CollectionType maxLogProcSize4CollectionType
     * @return String
     */
    public static String arrToJSONString(Object[] objAny, int maxLogProcSize4CollectionType) {
        if (objAny == null) {
            return null;
        }
        try {
            StringBuilder strb = new StringBuilder();
            for (int i = 0; i < Array.getLength(objAny); i++) {
                Object obj = Array.get(objAny, i);
                if (strb.length() > 0) {
                    strb.append(",");
                }
                strb.append(objToJSONString(obj, maxLogProcSize4CollectionType));
            }
            return "[" + strb.toString() + "]";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * PropertyPreFilterEx
     */
    static class PropertyPreFilterEx implements PropertyFilter {
        int maxLogProcSize4CollectionType;

        public PropertyPreFilterEx(int maxLogProcSize4CollectionType) {
            this.maxLogProcSize4CollectionType = maxLogProcSize4CollectionType;
        }


        @Override
        public boolean apply(Object object, String name, Object value) {
            if (value == null || value instanceof ServletResponse || value instanceof ServletRequest) {
                return false;
            } else if ((value instanceof List) || (value instanceof Set)) {
                Collection<?> lst = (Collection<?>) value;
                if (lst.size() > maxLogProcSize4CollectionType) {
                    log.warn("Skip log due to collection too large: name:{},size:{},limit:{}", name, ((Collection<?>) value).size(), maxLogProcSize4CollectionType);
                    return false;
                }
            } else if (value.getClass().isArray() && Array.getLength(value) > maxLogProcSize4CollectionType) {
                log.warn("Skip log due to array size large: name:{},size:{},limit:{}", name, ((Collection<?>) value).size(), maxLogProcSize4CollectionType);
                return false;
            }
            Package pkg = value.getClass().getPackage();
            if (pkg == null) {
                return false;
            }
            String pkgName = pkg.getName();
            return (pkgName.startsWith("java.lang") || pkgName.startsWith("java.util") || pkgName.startsWith("java.sql") || pkgName.startsWith("java.math"));
        }
    }
}
