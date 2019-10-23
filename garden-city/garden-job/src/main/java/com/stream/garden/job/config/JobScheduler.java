package com.stream.garden.job.config;

import com.stream.garden.job.constants.JobConstant;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author garden
 * @date 2019-10-23 11:22
 */
public class JobScheduler {
    private static Logger logger = LoggerFactory.getLogger(JobScheduler.class);

    private static Scheduler scheduler;

    /**
     * 新增job
     *
     * @param jobName  job名称
     * @param jobGroup job组
     * @param jobCron  job表达式
     * @param url      远程路径
     * @throws SchedulerException e
     */
    public static void addJob(String jobName, String jobGroup, String jobCron, String url, String params) throws SchedulerException {
        if (null == scheduler) {
            logger.debug("任务调度未启用");
            return;
        }
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            logger.debug("任务已存在");
        } else {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobCron).withMisfireHandlingInstructionDoNothing();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().forJob(jobKey).withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(JobConstant.JOB_DATA_KEY_URL, url);
            jobDataMap.put(JobConstant.JOB_DATA_KEY_PARAMS, params);
            JobDetail jobDetail = JobBuilder.newJob(RemoteJob.class).usingJobData(jobDataMap).withIdentity(jobKey).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    /**
     * 删除job
     *
     * @param jobName  job名称
     * @param jobGroup job组
     * @return boolean
     */
    public static boolean deleteJob(String jobName, String jobGroup) {
        if (null == scheduler) {
            logger.debug("任务调度未启用");
            return false;
        }
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
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

    static void setScheduler(Scheduler scheduler) {
        JobScheduler.scheduler = scheduler;
    }
}
