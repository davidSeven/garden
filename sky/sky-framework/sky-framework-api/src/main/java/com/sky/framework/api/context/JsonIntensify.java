package com.sky.framework.api.context;

/**
 * @date 2020-11-10 010 13:55
 */
public class JsonIntensify {

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段增强名称
     */
    private String fieldIntensifyName;

    /**
     * 类加载类型
     * class加载
     * spring bean加载
     */
    private String loadClassType;

    /**
     * 类加载地址
     * class地址
     * spring bean名称
     */
    private String loadClassPath;

    public JsonIntensify() {
    }

    public JsonIntensify(String fieldName, String fieldIntensifyName, String loadClassType, String loadClassPath) {
        this.fieldName = fieldName;
        this.fieldIntensifyName = fieldIntensifyName;
        this.loadClassType = loadClassType;
        this.loadClassPath = loadClassPath;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldIntensifyName() {
        return fieldIntensifyName;
    }

    public void setFieldIntensifyName(String fieldIntensifyName) {
        this.fieldIntensifyName = fieldIntensifyName;
    }

    public String getLoadClassType() {
        return loadClassType;
    }

    public void setLoadClassType(String loadClassType) {
        this.loadClassType = loadClassType;
    }

    public String getLoadClassPath() {
        return loadClassPath;
    }

    public void setLoadClassPath(String loadClassPath) {
        this.loadClassPath = loadClassPath;
    }
}
