package com.forest.framework.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

public class ConverterBoolean implements Converter<String, Boolean> {

    @Override
    public Boolean convert(@NonNull String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if ("true".equalsIgnoreCase(source.trim())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
