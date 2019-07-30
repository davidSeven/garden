package com.stream.garden.framework.limit.core;

import com.stream.garden.framework.limit.annotation.Limit;
import com.stream.garden.framework.limit.config.LimitConfig;
import com.stream.garden.framework.limit.enums.LimitType;

import java.lang.annotation.Annotation;

/**
 * @author garden
 * @date 2019-07-20 10:52
 */
public class ApplicationHandlerLimit extends AbstractHandlerLimit {

    private LimitConfig limitConfig;
    private Limit limit;

    public ApplicationHandlerLimit(LimitConfig limitConfig) {
        this.limitConfig = limitConfig;
    }

    @Override
    public boolean handle(Limit limit) {
        if (null == limit) {
            limit = getLimit();
        }
        return super.handle(limit);
    }

    private Limit getLimit() {
        if (null == limit) {
            this.limit = new Limit() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return Limit.class;
                }

                @Override
                public String name() {
                    return limitConfig.getName();
                }

                @Override
                public String key() {
                    return limitConfig.getKey();
                }

                @Override
                public String prefix() {
                    return limitConfig.getPrefix();
                }

                @Override
                public int period() {
                    return limitConfig.getPeriod();
                }

                @Override
                public int count() {
                    return limitConfig.getCount();
                }

                @Override
                public LimitType limitType() {
                    return limitConfig.getLimitType();
                }
            };
        }
        return this.limit;
    }
}
