package com.stream.garden.system.user.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-06-19 11:27
 */
@Mapper
public interface IUserDao extends IBaseMapper<User, String> {
}
