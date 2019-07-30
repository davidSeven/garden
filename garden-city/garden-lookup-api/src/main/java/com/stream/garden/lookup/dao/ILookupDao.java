package com.stream.garden.lookup.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.lookup.model.Lookup;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 */
@Mapper
public interface ILookupDao extends IBaseMapper<Lookup, String> {
}
