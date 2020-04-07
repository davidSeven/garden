package com.stream.garden.job.config;

import com.stream.garden.job.service.ITaskService;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;

/**
 * @author garden
 * @date 2019-10-23 10:58
 */
@Component
@ConfigurationProperties(prefix = JobConfig.CONFIG_PREFIX)
public class JobConfig implements InitializingBean {
    static final String CONFIG_PREFIX = "garden.job";
    private Logger logger = LoggerFactory.getLogger(JobConfig.class);
    /**
     * 是否启用任务调度
     */
    private boolean enabled;

    private Scheduler scheduler;

    private ITaskService taskService;

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public JobConfig(ITaskService taskService) {
        // this.scheduler = scheduler;
        this.taskService = taskService;
    }

    /*@Bean
    public Scheduler scheduler() throws Exception {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            return schedulerFactory.getScheduler();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("初始化任务调度管理器异常");
        }
    }*/

    @Override
    public void afterPropertiesSet() throws Exception {
        // 启用任务调度
        if (enabled) {
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) this.applicationContext;
            BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();

            Class<Scheduler> schedulerClass = Scheduler.class;
            String className = schedulerClass.getName();
            String alias = "scheduler";

            // 动态注册Bean Scheduler
//            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SchedulerFactoryBean.class);
//            BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinitionBuilder.getBeanDefinition(), className, new String[]{alias});
//            BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, beanDefinitionRegistry);

            // 获取注册的Bean Scheduler
            // Object scheduler1 = this.applicationContext.getBean(alias);
            // Object scheduler2 = this.applicationContext.getBean(alias, schedulerClass);
            // 这里根据类型去获取会拿到多个实例，QuartzAutoConfiguration配置类已经注册了一个
            // Object scheduler3 = this.applicationContext.getBean(schedulerClass);

            // 拿到自己注册的实例
            this.scheduler = this.applicationContext.getBean(schedulerClass);

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
