package com.stream.garden.file.dao;

import com.stream.garden.file.model.FileInfo;
import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-09-26 14:20
 */
@Mapper
public interface IFileInfoDao extends IBaseMapper<FileInfo, String> {
}
