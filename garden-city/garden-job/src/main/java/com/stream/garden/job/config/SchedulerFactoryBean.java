package com.stream.garden.job.config;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author garden
 * @date 2020-04-07 13:49
 */
public class SchedulerFactoryBean implements FactoryBean<Scheduler> {

    @Override
    public Scheduler getObject() throws Exception {
        return new StdSchedulerFactory().getScheduler();
    }

    @Override
    public Class<?> getObjectType() {
        return Scheduler.class;
    }
}
