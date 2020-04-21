package com.stream.garden.file.dao;

import com.stream.garden.file.model.FileManage;
import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-09-26 14:16
 */
@Mapper
public interface IFileManageDao extends IBaseMapper<FileManage> {
}
