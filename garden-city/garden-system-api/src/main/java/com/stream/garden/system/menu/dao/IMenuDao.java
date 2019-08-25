package com.stream.garden.system.menu.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.menu.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author garden
 */
@Mapper
public interface IMenuDao extends IBaseMapper<Menu, String> {

    /**
     * 查询角色菜单
     *
     * @param roleId 角色id
     * @return 菜单
     */
    List<Menu> getRoleMenu(String roleId);
}
