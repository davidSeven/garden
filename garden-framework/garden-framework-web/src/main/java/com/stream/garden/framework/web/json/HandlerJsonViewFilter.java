package com.stream.garden.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * @author garden
 * @date 2020-04-07 19:43
 */
public interface HandlerJsonViewFilter {

    /**
     * 字段过滤
     *
     * @param currentPath 字段路径
     * @param fieldName   字段名称
     * @param value       字段值
     * @param jgen        jgen
     * @return boolean
     */
    boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen);

    /**
     * 字段强化
     *
     * @param fieldName 字段名称
     * @param value     字段值
     * @param jgen      jgen
     */
    default void fieldIntensify(String fieldName, Object value, JsonGenerator jgen) {

    }
}
