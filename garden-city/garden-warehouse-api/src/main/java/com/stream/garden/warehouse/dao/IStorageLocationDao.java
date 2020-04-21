package com.stream.garden.warehouse.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.warehouse.model.StorageLocation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-12-29 15:54
 */
@Mapper
public interface IStorageLocationDao extends IBaseMapper<StorageLocation> {
}
