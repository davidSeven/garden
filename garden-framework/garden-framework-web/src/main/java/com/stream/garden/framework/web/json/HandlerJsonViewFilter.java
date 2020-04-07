package com.stream.garden.framework.web.json;

import com.fasterxml.jackson.core.JsonGenerator;

/**
 * @author garden
 * @date 2020-04-07 19:43
 */
public interface HandlerJsonViewFilter {

    boolean filter(String currentPath, String fieldName, Object value, JsonGenerator jgen);
}
