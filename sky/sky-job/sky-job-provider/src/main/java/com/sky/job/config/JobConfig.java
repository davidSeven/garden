package com.sky.job.config;

import com.sky.job.service.JobService;
import com.sky.job.util.JobScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @date 2020-11-23 023 14:04
 */
@Component
public class JobConfig implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobService jobService;

    @Bean
    public Scheduler scheduler() throws Exception {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            return schedulerFactory.getScheduler();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("初始化任务调度管理器异常");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JobScheduler.setScheduler(this.scheduler);
        try {
            // 初始化任务
            this.jobService.initJob();
        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
            throw se;
        }
    }
}
