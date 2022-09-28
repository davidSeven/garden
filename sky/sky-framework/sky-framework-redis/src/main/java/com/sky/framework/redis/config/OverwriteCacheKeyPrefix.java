package com.sky.framework.redis.config;

import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.lang.NonNull;

public class OverwriteCacheKeyPrefix implements CacheKeyPrefix {

    @Override
    @NonNull
    public String compute(@NonNull String cacheName) {
        // default return ::
        return cacheName + ":";
    }
}
