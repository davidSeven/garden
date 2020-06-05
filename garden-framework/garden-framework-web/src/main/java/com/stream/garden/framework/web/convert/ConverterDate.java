package com.stream.garden.framework.web.convert;

import com.stream.garden.framework.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author zhangyuyuan
 * @date 2020-06-05 17:08
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
