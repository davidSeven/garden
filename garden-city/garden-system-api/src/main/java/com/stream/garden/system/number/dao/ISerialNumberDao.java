package com.stream.garden.system.number.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.number.model.SerialNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author garden
 * @date 2020-06-04 17:13
 */
@Mapper
public interface ISerialNumberDao extends IBaseMapper<SerialNumber> {

    /**
     * 根据code查询
     *
     * @param code code
     * @return SerialNumber
     */
    SerialNumber getByCode(@Param("code") String code);
}
