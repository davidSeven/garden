package com.sky.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * @date 2020-11-04 004 14:29
 */
public abstract class AbstractFieldExpandInterface implements FieldExpandInterface {

    @Override
    public boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen) {
        return false;
    }

    @Override
    public void fieldIntensify(String currentPath, String fieldName, Object value, JsonGenerator jgen) {

    }
}
