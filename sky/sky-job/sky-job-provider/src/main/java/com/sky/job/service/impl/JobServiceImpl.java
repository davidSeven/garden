package com.sky.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.job.api.model.Job;
import com.sky.job.constant.JobConstant;
import com.sky.job.dao.JobDao;
import com.sky.job.service.JobService;
import com.sky.job.util.JobScheduler;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @date 2020-12-14 014 14:11
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {

    @Override
    public void initJob() throws SchedulerException {
        // 查询启用的任务
        LambdaQueryWrapper<Job> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Job::getEnable, JobConstant.JOB_TASK_STATE_ENABLED);
        List<Job> JobList = super.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(JobList)) {
            for (Job Job : JobList) {
                // 添加任务
                String jobId = String.valueOf(Job.getId());
                JobScheduler.addJob(jobId, Job.getTaskGroup(), Job.getCronTime());
            }
        }
        log.debug("---------------------------------------------------");
        log.debug("---------------------------------------------------");
        log.debug("任务调度完成初始化");
        log.debug("---------------------------------------------------");
        log.debug("---------------------------------------------------");
    }

    @Override
    public void begin(String jobId, Date processDate) {
        // 初始化任务执行信息
        Job Job = new Job();
        Job.setId(Long.valueOf(jobId));
        // 初始化当前次数
        Job.setCurrentRedoTime(0);
        Job.setProcessStatus(JobConstant.JOB_TASK_PROCESS_STATUS_EXECUTING);
        Job.setProcessDate(processDate);
        super.updateById(Job);
    }

    @Override
    public void end(String jobId, Date processDate, Date processEndDate, String processMsg) {
        this.end(jobId, processDate, processEndDate, processMsg, JobConstant.JOB_TASK_PROCESS_STATUS_UN_EXECUTED);
    }

    private void end(String jobId, Date processDate, Date processEndDate, String processMsg, int processStatus) {
        Job Job = new Job();
        Job.setId(Long.valueOf(jobId));
        Job.setProcessStatus(processStatus);
        Job.setProcessEndDate(processEndDate);
        Job.setProcessStamp(processEndDate.getTime() - processDate.getTime());
        Job.setProcessMsg(processMsg);
        super.updateById(Job);
    }

    @Override
    public void endByFailed(String jobId, Date processDate, Date processEndDate, String processMsg) {
        this.end(jobId, processDate, processEndDate, processMsg, JobConstant.JOB_TASK_PROCESS_STATUS_FAILED);
    }

    @Override
    public void retry(String jobId, int redoTime, String processMsg) {
        Job Job = new Job();
        Job.setId(Long.valueOf(jobId));
        Job.setProcessMsg(processMsg);
        Job.setCurrentRedoTime(redoTime);
        super.updateById(Job);
    }

    @Override
    public boolean enabled(Long id) {
        if (null == id) {
            return false;
        }
        Job Job = super.getById(id);
        if (null == Job) {
            throw new CommonException(999, "任务信息不存在");
        }
        if (JobConstant.JOB_TASK_STATE_ENABLED == Job.getEnable()) {
            return true;
        }
        // 修改状态为启用
        Job updateJob = new Job();
        updateJob.setId(Job.getId());
        updateJob.setEnable(JobConstant.JOB_TASK_STATE_ENABLED);
        boolean update = super.updateById(updateJob);
        // 添加到任务中
        try {
            JobScheduler.addJob(String.valueOf(Job.getId()), Job.getTaskGroup(), Job.getCronTime());
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new CommonException(999, "任务启用失败");
        }
        return update;
    }

    @Override
    public boolean disabled(Long id) {
        if (null == id) {
            return false;
        }
        Job Job = super.getById(id);
        if (null == Job) {
            throw new CommonException(999, "任务信息不存在");
        }
        if (JobConstant.JOB_TASK_STATE_DISABLED == Job.getEnable()) {
            return true;
        }
        // 修改状态为禁用
        Job updateJob = new Job();
        updateJob.setId(Job.getId());
        updateJob.setEnable(JobConstant.JOB_TASK_STATE_DISABLED);
        boolean update = super.updateById(updateJob);
        // 从任务中移除
        JobScheduler.deleteJob(String.valueOf(Job.getId()), Job.getTaskGroup());
        return update;
    }

    @Override
    public boolean trigger(Long id) {
        if (null == id) {
            return false;
        }
        Job Job = super.getById(id);
        if (null == Job) {
            throw new CommonException(999, "任务信息不存在");
        }
        return JobScheduler.triggerJob(String.valueOf(Job.getId()), Job.getTaskGroup());
    }
}
