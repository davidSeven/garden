package com.sky.framework.interceptor;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.sky.framework.api.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

@Component
public class ContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    private final Logger logger = LoggerFactory.getLogger(ContextHystrixConcurrencyStrategy.class);

    private final HystrixConcurrencyStrategy delegate;

    /**
     * 处理feign调用时无法获取上下文信息
     * 参考：SleuthHystrixConcurrencyStrategy
     */
    public ContextHystrixConcurrencyStrategy() {
        this.delegate = HystrixPlugins.getInstance().getConcurrencyStrategy();
        if (this.delegate instanceof ContextHystrixConcurrencyStrategy) {
            this.logger.info("instance strategy is ContextHystrixConcurrencyStrategy");
            return;
        }
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        if (callable instanceof HystrixContextWrapper) {
            this.logger.info("callable is HystrixContextWrapper");
            return callable;
        }
        Callable<T> wrapCallable = null != this.delegate ? this.delegate.wrapCallable(callable) : callable;
        return wrapCallable instanceof HystrixContextWrapper ? wrapCallable : new HystrixContextWrapper<>(wrapCallable);
    }

    public static class HystrixContextWrapper<V> implements Callable<V> {

        private final RequestAttributes requestAttributes;
        private final RequestContext requestContext;
        private final Callable<V> delegate;

        public HystrixContextWrapper(Callable<V> delegate) {
            this.requestAttributes = RequestContextHolder.getRequestAttributes();
            this.requestContext = RequestContext.getCurrentContext();
            this.delegate = delegate;
        }

        @Override
        public V call() throws Exception {
            try {
                // 执行子线程之前赋值
                RequestContextHolder.setRequestAttributes(this.requestAttributes);
                RequestContext.setCurrentContext(this.requestContext);
                return this.delegate.call();
            } finally {
                // 执行完成清空值
                RequestContextHolder.setRequestAttributes(null);
                RequestContext.getCurrentContext().close();
            }
        }
    }
}
