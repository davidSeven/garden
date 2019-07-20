package com.stream.garden.framework.limit.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 控制全局限流
 *
 * @author garden
 * @date 2019-07-20 9:38
 */
@Component
public class LimitControl implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
