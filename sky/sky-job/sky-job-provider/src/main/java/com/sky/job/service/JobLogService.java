package com.sky.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.job.api.model.JobLog;

/**
 * @date 2020-12-14 014 14:09
 */
public interface JobLogService extends IService<JobLog> {

    /**
     * 清理多少天之前的日志
     *
     * @param days days
     * @return int
     */
    int clearLogs(int days);
}
