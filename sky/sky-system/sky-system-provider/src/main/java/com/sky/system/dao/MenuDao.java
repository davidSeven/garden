package com.sky.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.system.api.model.Menu;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2020-12-16 016 11:37
 */
public interface MenuDao extends BaseMapper<Menu> {

    int increaseChildrenSize(@Param("id") Long id);

    int decreaseChildrenSize(@Param("id") Long id);
}
