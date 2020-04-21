package com.stream.garden.lookup.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.lookup.model.LookupItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 */
@Mapper
public interface ILookupItemDao extends IBaseMapper<LookupItem> {
}
