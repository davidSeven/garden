package com.sky.job.util;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @date 2020-11-23 023 14:06
 */
public final class JobScheduler {

    private static final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    private static Scheduler scheduler;

    /**
     * 新增job
     *
     * @param jobId    jobId
     * @param jobGroup job组
     * @param jobCron  job表达式
     * @throws SchedulerException e
     */
    public static void addJob(String jobId, String jobGroup, String jobCron) throws SchedulerException {
        if (null == JobScheduler.scheduler) {
            logger.debug("任务调度未启用");
            return;
        }
        JobKey jobKey = JobKey.jobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            logger.debug("任务已存在");
        } else {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobCron).withMisfireHandlingInstructionDoNothing();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId, jobGroup);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().forJob(jobKey).withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            JobDetail jobDetail = JobBuilder.newJob(CallJob.class).withIdentity(jobKey).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    /**
     * 删除job
     *
     * @param jobId    jobId
     * @param jobGroup job组
     * @return boolean
     */
    public static boolean deleteJob(String jobId, String jobGroup) {
        if (null == scheduler) {
            logger.debug("任务调度未启用");
            return false;
        }
        try {
            JobKey jobKey = JobKey.jobKey(jobId, jobGroup);
            if (scheduler.checkExists(jobKey)) {
                logger.debug("存在任务，执行删除操作");
                scheduler.deleteJob(jobKey);
            } else {
                logger.debug("不存在任务");
            }
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 触发任务
     *
     * @param jobId    jobId
     * @param jobGroup jobGroup
     * @return boolean
     */
    public static boolean triggerJob(String jobId, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobId, jobGroup);
        try {
            if (scheduler.checkExists(jobKey)) {
                scheduler.triggerJob(jobKey);
                return true;
            } else {
                logger.debug("任务不存在，触发任务失败。jobId:{},jobGroup:{}", jobId, jobGroup);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取当前job数量
     *
     * @return int
     */
    public static int getJobSize() {
        if (null == scheduler) {
            logger.debug("任务调度未启用");
            return 0;
        }
        try {
            if (scheduler.isShutdown()) {
                return 0;
            }
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
            return jobKeys.size();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setScheduler(Scheduler scheduler) {
        if (null == JobScheduler.scheduler) {
            JobScheduler.scheduler = scheduler;
        }
    }
}
