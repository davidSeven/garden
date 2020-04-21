package com.stream.garden.system.function.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.function.model.FunctionField;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2020-01-22 11:01
 */
@Mapper
public interface IFunctionFieldDao extends IBaseMapper<FunctionField> {
}
