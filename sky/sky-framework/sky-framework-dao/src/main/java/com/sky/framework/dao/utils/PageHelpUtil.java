package com.sky.framework.dao.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.utils.BeanHelpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @date 2020-05-06 16:12
 */
public final class PageHelpUtil {
    private static final Logger logger = LoggerFactory.getLogger(PageHelpUtil.class);

    /**
     * 分页对象转换
     *
     * @param iPage       iPage
     * @param targetClass targetClass
     * @param <T>         T
     * @return IPage
     */
    public static <T> IPage<T> convertPage(IPage<?> iPage, Class<T> targetClass) {
        IPage<T> dtoPage = new Page<>(iPage.getCurrent(), iPage.getSize());
        List<?> records = iPage.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            dtoPage.setRecords(BeanHelpUtil.convertDtoList(records, targetClass));
            dtoPage.setTotal(iPage.getTotal());
            dtoPage.setPages(iPage.getPages());
        }
        return dtoPage;
    }

    /**
     * 返回空的分页对象
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @param <T>      T
     * @return IPage
     */
    public static <T> IPage<T> emptyPage(int pageNum, int pageSize) {
        IPage<T> dtoPage = new Page<>(pageNum, pageSize);
        dtoPage.setTotal(0);
        dtoPage.setPages(1);
        return dtoPage;
    }
}
