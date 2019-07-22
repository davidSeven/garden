package com.stream.garden.system.role.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.role.model.RoleFunction;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019/7/22 22:27
 */
@Mapper
public interface IRoleFunctionDao extends IBaseMapper<RoleFunction, String> {

    int deleteByRoleId(RoleFunction roleFunction);
}
