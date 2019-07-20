package com.stream.garden.framework.limit.core;

import com.stream.garden.framework.limit.annotation.Limit;

/**
 * @author garden
 * @date 2019-07-20 9:39
 */
public interface HandlerLimit {

    /**
     * 是否可以访问
     *
     * @return boolean
     */
    boolean handle(Limit limit);
}
