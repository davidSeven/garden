package com.stream.garden.system.user.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.user.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-06-22 10:58
 */
@Mapper
public interface IUserRoleDao extends IBaseMapper<UserRole> {
}
