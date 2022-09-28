package com.sky.del.config;

import cn.hutool.core.thread.NamedThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class DeliveryConfiguration {
    private static final int availableProcessors;

    static {
        // 获取机器核数
        availableProcessors = Runtime.getRuntime().availableProcessors();
    }

    public static final String THREAD_POOL_EXECUTOR_ORDER_DELETE = "ThreadPoolExecutor_OrderDelete";

    @Bean(THREAD_POOL_EXECUTOR_ORDER_DELETE)
    public ThreadPoolExecutor threadPoolExecutorDelOutboundReviewed() {
        // 核心线程数量
        int corePoolSize = availableProcessors + 1;
        int maximumPoolSize = availableProcessors * 4;
        // 队列
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1024);
        // 核心和最大一致
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10, TimeUnit.SECONDS, queue);
        // 线程池工厂
        NamedThreadFactory threadFactory = new NamedThreadFactory("OrderDelete", false);
        threadPoolExecutor.setThreadFactory(threadFactory);
        // 拒绝策略由主线程执行
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
