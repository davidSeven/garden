package com.sky.framework.enums;

/**
 * 枚举接口
 */
public interface EnumInterface {

    /**
     * 默认前缀
     */
    String prefix = "$";

    /**
     * 前缀
     *
     * @return String
     */
    default String prefix() {
        return prefix;
    }

    /**
     * 获取编码
     *
     * @return String
     */
    default String getCode() {
        if (this instanceof Enum) {
            return ((Enum<?>) this).name().substring(this.prefix().length());
        }
        return "";
    }
}