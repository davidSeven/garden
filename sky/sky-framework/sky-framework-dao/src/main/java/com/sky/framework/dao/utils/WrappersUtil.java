package com.sky.framework.dao.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;

/**
 * @date 2020-10-28 028 14:24
 */
public final class WrappersUtil {

    public static <T> void like(LambdaQueryWrapper<T> queryWrapper, SFunction<T, ?> sFunction, String val) {
        if (StringUtils.isNotEmpty(val)) {
            queryWrapper.like(sFunction, val);
        }
    }

    public static <T> void eq(LambdaQueryWrapper<T> queryWrapper, SFunction<T, ?> sFunction, String val) {
        if (StringUtils.isNotEmpty(val)) {
            queryWrapper.eq(sFunction, val);
        }
    }
}
