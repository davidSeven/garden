package com.stream.garden.framework.util;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author city
 */
public class CollectionUtil {

    /**
     * 判断集合是否为空
     *
     * @param collection 集合对象
     * @return 为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null) || (collection.size() == 0);
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 集合对象
     * @return 不为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map map
     * @return 为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (null == map || map.isEmpty());
    }
}
