package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;

import java.util.Stack;

/**
 * @date 2020-11-04 004 14:29
 */
public interface FieldExpandInterface {

    /**
     * 追加字段
     *
     * @param currentPath currentPath
     * @param path        path
     * @param jgen        jgen
     * @param object      object
     */
    default void append(String currentPath, Stack<String> path, JsonGenerator jgen, Object object) {
    }

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
