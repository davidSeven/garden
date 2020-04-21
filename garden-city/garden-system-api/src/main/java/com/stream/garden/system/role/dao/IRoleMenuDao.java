package com.stream.garden.system.role.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.role.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019/8/25 20:17
 */
@Mapper
public interface IRoleMenuDao extends IBaseMapper<RoleMenu> {

    int deleteByRoleId(RoleMenu roleMenu);
}
