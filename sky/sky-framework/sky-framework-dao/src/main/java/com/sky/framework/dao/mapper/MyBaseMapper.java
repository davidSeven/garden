package com.sky.framework.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;

/**
 * @date 2020-11-03 003 13:50
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int physicalDeleteById(Serializable id);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     */
    int physicalDelete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int physicalDeleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
}
