package com.stream.garden.job.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.job.model.TaskLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-10-22 17:12
 */
@Mapper
public interface ITaskLogDao extends IBaseMapper<TaskLog, String> {
}
