package com.forest.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author forest
 * @date 2019-07-15 10:10
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
