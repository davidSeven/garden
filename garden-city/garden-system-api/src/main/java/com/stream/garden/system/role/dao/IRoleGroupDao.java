package com.stream.garden.system.role.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.role.model.RoleGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-06-22 11:07
 */
@Mapper
public interface IRoleGroupDao extends IBaseMapper<RoleGroup> {
}
