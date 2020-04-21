package com.stream.garden.excel.dao;

import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-11-04 16:07
 */
@Mapper
public interface IExcelConfigDao extends IBaseMapper<ExcelConfig> {
}
