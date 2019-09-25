package com.stream.garden.framework.util;

/**
 * condition util
 *
 * @author garden
 * @date 2019-09-23 10:01
 */
public class ConditionUtil {

    /**
     * 验证 string 是否存在与数组 strings 中
     *
     * @param string  值
     * @param strings 数组
     * @return boolean
     */
    public static boolean has(String string, String... strings) {
        boolean stringIsNull = null == string;
        if (stringIsNull) {
            for (String item : strings) {
                if (null == item) {
                    return true;
                }
            }
        } else {
            for (String item : strings) {
                if (string.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 所有条件都成立
     *
     * @param booleans 条件数组
     * @return boolean
     */
    public static boolean and(boolean... booleans) {
        for (boolean item : booleans) {
            if (!item) {
                return false;
            }
        }
        return true;
    }

    /**
     * 条件成立一个
     *
     * @param booleans 条件数组
     * @return boolean
     */
    public static boolean or(boolean... booleans) {
        for (boolean item : booleans) {
            if (item) {
                return true;
            }
        }
        return false;
    }
}
