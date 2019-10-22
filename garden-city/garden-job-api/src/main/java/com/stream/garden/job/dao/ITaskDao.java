package com.stream.garden.job.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.job.model.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-10-22 16:55
 */
@Mapper
public interface ITaskDao extends IBaseMapper<Task, String> {
}
