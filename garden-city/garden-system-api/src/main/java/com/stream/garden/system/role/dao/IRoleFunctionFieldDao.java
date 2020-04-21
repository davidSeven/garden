package com.stream.garden.system.role.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.role.model.RoleFunctionField;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019/7/22 22:27
 */
@Mapper
public interface IRoleFunctionFieldDao extends IBaseMapper<RoleFunctionField> {

    int deleteByRoleId(RoleFunctionField params);
}
