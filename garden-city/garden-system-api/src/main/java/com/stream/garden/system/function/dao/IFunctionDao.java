package com.stream.garden.system.function.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.function.model.Function;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-07-20 15:44
 */
@Mapper
public interface IFunctionDao extends IBaseMapper<Function, String> {
}
