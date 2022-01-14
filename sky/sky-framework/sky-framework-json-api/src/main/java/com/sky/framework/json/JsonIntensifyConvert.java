package com.sky.framework.json;

/**
 * @date 2020-11-10 010 14:05
 */
public interface JsonIntensifyConvert {

    /**
     * 转换
     *
     * @param value value
     * @return String
     */
    String convert(String value);

    public static class OriginalJsonIntensifyConvert implements JsonIntensifyConvert {

        @Override
        public String convert(String value) {
            return value;
        }
    }

    public static class WrapJsonIntensifyConvert implements JsonIntensifyConvert {

        @Override
        public String convert(String value) {
            return "[" + value + "]";
        }
    }
}
