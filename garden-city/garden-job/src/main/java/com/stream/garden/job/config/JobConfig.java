package com.stream.garden.job.config;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.job.service.ITaskService;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-10-23 10:58
 */
@Component
@ConfigurationProperties(prefix = JobConfig.CONFIG_PREFIX)
public class JobConfig implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(JobConfig.class);

    static final String CONFIG_PREFIX = "garden.job";

    /**
     * 是否启用任务调度
     */
    private boolean enabled;

    private Scheduler scheduler;

    private ITaskService taskService;

    @Autowired
    public JobConfig(Scheduler scheduler, ITaskService taskService) {
        this.scheduler = scheduler;
        this.taskService = taskService;
    }

    @Bean
    public Scheduler scheduler() throws Exception {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            return schedulerFactory.getScheduler();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("初始化任务调度管理器异常");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 启用任务调度
        if (enabled) {
            JobScheduler.setScheduler(this.scheduler);

            // 初始化任务
            taskService.initJob();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
