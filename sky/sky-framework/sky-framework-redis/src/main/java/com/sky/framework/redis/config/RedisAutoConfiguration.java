package com.sky.framework.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    public static long TIME_OUT = 1 * 24 * 60 * 60;

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public KeyGenerator customKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName()).append(":");
                sb.append(method.getName()).append(":");

                List<String> args = new ArrayList<String>();
                args.add(target.getClass().getName());
                args.add(method.getName());

                for (Object obj : params) {
                    args.add(obj.toString());
                }

                SimpleKey simpleKey = new SimpleKey(args.toArray());
                sb.append(simpleKey.hashCode());
                return sb.toString();
            }
        };
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(TIME_OUT)).disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(defaultCacheConfig).build();
        return cacheManager;
    }

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//        List<CaffeineCache> caffeineCaches = new ArrayList<CaffeineCache>();
//
//        List<CacheConfig> cacheConfigs = initCacheConfig();
//        if (!CollectionUtils.isEmpty(cacheConfigs)) {
//            cacheConfigs.stream().forEach(cacheConfig -> {
//                if (StringUtil.isNotEmpty(cacheConfig.getCacheName())) {
//                    int maximumSize = 15000;
//                    int expireAfterWrite = 120;
//                    if (cacheConfig.getMaximumSize() != 0) {
//                        maximumSize = cacheConfig.getMaximumSize();
//                    }
//                    if (cacheConfig.getExpireAfterWrite() != 0) {
//                        expireAfterWrite = cacheConfig.getExpireAfterWrite();
//                    }
//                    CaffeineCache caffeineCache = new CaffeineCache(cacheConfig.getCacheName(),
//                            Caffeine.newBuilder().recordStats().expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS).maximumSize(maximumSize).build());
//                    caffeineCaches.add(caffeineCache);
//                }
//
//            });
//        }
//        simpleCacheManager.setCaches(caffeineCaches);
//        return simpleCacheManager;
//    }

    @Bean
    public RedissonClient redissonSingle() {
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();
        Config config = new Config();
        config.setNettyThreads(8);
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + host + ":" + port);
        // 最小连接数
        serverConfig.setConnectionMinimumIdleSize(4);
        serverConfig.setConnectionPoolSize(32);
        serverConfig.setDatabase(redisProperties.getDatabase());
        if (StringUtils.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

}