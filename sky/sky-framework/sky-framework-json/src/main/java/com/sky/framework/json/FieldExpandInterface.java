package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * @date 2020-11-04 004 14:29
 */
public interface FieldExpandInterface {

    /**
     * 追加字段
     *
     * @param jgen jgen
     */
    void append(JsonGenerator jgen);

    /**
     * 字段过滤
     *
     * @param currentPath currentPath
     * @param fieldName   fieldName
     * @param value       value
     * @param jgen        jgen
     * @return boolean
     */
    boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen);

    /**
     * 字段增强
     *
     * @param currentPath currentPath
     * @param fieldName   字段名称
     * @param value       字段值
     * @param jgen        jgen
     */
    void fieldIntensify(String currentPath, String fieldName, Object value, JsonGenerator jgen);
}
