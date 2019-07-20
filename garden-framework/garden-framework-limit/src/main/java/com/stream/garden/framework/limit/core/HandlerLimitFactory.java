package com.stream.garden.framework.limit.core;

import com.stream.garden.framework.limit.config.LimitConfig;
import com.stream.garden.framework.limit.enums.LimitRule;
import com.stream.garden.framework.web.util.ApplicationUtil;

/**
 * @author garden
 * @date 2019-07-20 9:42
 */
public class HandlerLimitFactory {

    private LimitConfig config;

    private HandlerLimit methodHandlerLimit;
    private HandlerLimit applicationHandlerLimit;
    private HandlerLimit applicationAndMethodHandlerLimit;
    private HandlerLimit applicationConfigHandlerLimit;

    private HandlerLimitFactory() {
        this.config = ApplicationUtil.getBeans(LimitConfig.class);
        this.methodHandlerLimit = new MethodHandlerLimit();
        this.applicationHandlerLimit = new ApplicationHandlerLimit(config);
        this.applicationAndMethodHandlerLimit = new ApplicationAndMethodHandlerLimit();
        this.applicationConfigHandlerLimit = new ApplicationConfigHandlerLimit();
    }

    public static HandlerLimitFactory getInstance() {
        return HandlerLimitFactoryInstance.instance;
    }

    public HandlerLimit builder(LimitRule limitRule) {
        if (null == config || null == limitRule) {
            return null;
        }
        if (LimitRule.METHOD.equals(limitRule)) {
            return methodHandlerLimit;
        } else if (LimitRule.APPLICATION.equals(limitRule)) {
            return applicationHandlerLimit;
        } else if (LimitRule.APPLICATION_AND_METHOD.equals(limitRule)) {
            return applicationAndMethodHandlerLimit;
        } else if (LimitRule.APPLICATION_CONFIG.equals(limitRule)) {
            return applicationConfigHandlerLimit;
        }
        return null;
    }

    private static class HandlerLimitFactoryInstance {
        private static final HandlerLimitFactory instance;

        static {
            instance = new HandlerLimitFactory();
        }
    }
}
