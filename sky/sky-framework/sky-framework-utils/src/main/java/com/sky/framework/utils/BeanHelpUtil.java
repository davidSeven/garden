package com.sky.framework.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020-10-09 009 15:04
 */
public final class BeanHelpUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanHelpUtil.class);

    public static <T> T convertDto(Object source, Class<T> targetClass) {
        if (null == source) {
            return null;
        }
        T t = getInstance(targetClass);
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T> T getInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        // 处理异常
        logger.error("实例化Bean异常，{}", targetClass);
        throw new RuntimeException("实例化Bean异常");
    }

    public static <T> List<T> convertDtoList(List<?> list, Class<T> targetClass) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<T> dtoList = new ArrayList<>();
            for (Object object : list) {
                dtoList.add(convertDto(object, targetClass));
            }
            return dtoList;
        }
        return null;
    }
}
