package com.stream.garden.system.group.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.group.model.Group;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-06-22 11:04
 */
@Mapper
public interface IGroupDao extends IBaseMapper<Group> {
}
