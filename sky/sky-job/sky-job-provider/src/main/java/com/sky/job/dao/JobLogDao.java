package com.sky.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.job.api.model.JobLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @date 2020-12-14 014 14:09
 */
public interface JobLogDao extends BaseMapper<JobLog> {

    /**
     * 清理多少天之前的日志
     *
     * @param date date
     * @return int
     */
    int clearLogs(@Param("date") Date date);
}
