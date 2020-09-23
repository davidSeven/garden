package com.forest.framework.config;

import com.forest.framework.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 *
 */
@Slf4j
public class ConverterDate implements Converter<String, Date> {

    @Override
    public Date convert(@NonNull String source) {
        try {
            return DateUtil.parse(source);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}