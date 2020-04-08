package com.stream.garden.system.function.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.function.model.FunctionFieldType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2020-04-08 19:12
 */
@Mapper
public interface IFunctionFieldTypeDao extends IBaseMapper<FunctionFieldType, String> {
}
