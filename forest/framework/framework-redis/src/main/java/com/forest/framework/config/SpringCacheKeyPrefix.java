package com.forest.framework.config;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author forest
 * @date 2019-07-15 11:25
 */
public class SpringCacheKeyPrefix implements CacheKeyPrefix {

    @Override
    public String compute(String cacheName) {
        // default return ::
        return cacheName + ":";
    }
}
