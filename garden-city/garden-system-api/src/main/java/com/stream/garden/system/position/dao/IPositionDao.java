package com.stream.garden.system.position.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.position.model.Position;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019/7/21 12:24
 */
@Mapper
public interface IPositionDao extends IBaseMapper<Position, String> {
}
