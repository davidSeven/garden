package com.sky.framework.json;

import com.alibaba.fastjson.JSON;

/**
 * @date 2020-11-10 010 14:05
 */
public interface JsonIntensifyAppend {

    /**
     * 转换
     *
     * @param object object
     * @return String
     */
    String convert(Object object);

    public static class OriginalJsonIntensifyAppend implements JsonIntensifyAppend {

        @Override
        public String convert(Object object) {
            return JSON.toJSONString(object);
        }
    }
}
