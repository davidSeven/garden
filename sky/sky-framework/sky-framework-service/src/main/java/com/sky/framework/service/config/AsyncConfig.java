package com.sky.framework.service.config;

import com.sky.framework.service.exception.BusinessAsyncUncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @date 2020-11-05 005 14:33
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    private final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        logger.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(5);
        // 配置最大线程数
        executor.setMaxPoolSize(5);
        // 配置队列大小
        executor.setQueueCapacity(99999);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // 设置拒绝策略：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new BusinessAsyncUncaughtExceptionHandler();
    }
}
