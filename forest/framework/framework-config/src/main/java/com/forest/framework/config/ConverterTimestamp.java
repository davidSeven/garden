package com.forest.framework.config;

import com.forest.framework.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 */
@Slf4j
public class ConverterTimestamp implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(@NonNull String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            if (source.matches("^\\d{13}$")) {
                return new Timestamp(Long.parseLong(source));
            }
            Date date = DateUtil.parse(source);
            if (date == null) {
                return null;
            }
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
