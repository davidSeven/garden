package com.sky.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.job.api.model.Job;
import org.quartz.SchedulerException;

import java.util.Date;

/**
 * @date 2020-12-14 014 14:10
 */
public interface JobService extends IService<Job> {

    /**
     * 初始化任务
     */
    void initJob() throws SchedulerException;

    /**
     * 任务开始
     *
     * @param jobId       jobId
     * @param processDate processDate
     */
    void begin(String jobId, Date processDate);

    /**
     * 任务结束
     *
     * @param jobId          jobId
     * @param processDate    processDate
     * @param processEndDate processEndDate
     * @param processMsg     processMsg
     */
    void end(String jobId, Date processDate, Date processEndDate, String processMsg);

    /**
     * 任务结束
     *
     * @param jobId          jobId
     * @param processDate    processDate
     * @param processEndDate processEndDate
     * @param processMsg     processMsg
     */
    void endByFailed(String jobId, Date processDate, Date processEndDate, String processMsg);

    /**
     * 重试
     *
     * @param jobId      jobId
     * @param redoTime   redoTime
     * @param processMsg processMsg
     */
    void retry(String jobId, int redoTime, String processMsg);

    /**
     * 启用
     *
     * @param id id
     * @return boolean
     */
    boolean enabled(Long id);

    /**
     * 禁用
     *
     * @param id id
     * @return boolean
     */
    boolean disabled(Long id);

    /**
     * 触发任务
     *
     * @param id id
     * @return boolean
     */
    boolean trigger(Long id);
}
