package com.sky.framework.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    private final Jackson2JsonRedisSerializer<Object> jacksonSeial;
    private final StringRedisSerializer redisSerializer;
    private final JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

    public RedisAutoConfiguration() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 序列化的时候序列对象的所有属性
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 反序列化的时候如果多了其他属性,不抛出异常
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jacksonSeial.setObjectMapper(om);
        redisSerializer = new StringRedisSerializer();
        jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
    }

    public static long TIME_OUT = 1 * 24 * 60 * 60;

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public KeyGenerator customKeyGenerator() {
        return new KeyGenerator() {
            @Override
            @NonNull
            public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName()).append(":");
                sb.append(method.getName()).append(":");

                List<String> args = new ArrayList<>();
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
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(TIME_OUT)).disableCachingNullValues()
                .computePrefixWith(new OverwriteCacheKeyPrefix())
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializationRedisSerializer));
        return RedisCacheManager.builder(factory).cacheDefaults(defaultCacheConfig).build();
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
        SingleServerConfig serverConfig;
        if (redisProperties.isSsl()) {
            serverConfig = config.useSingleServer()
                    .setAddress("rediss://" + host + ":" + port);
        } else {
            serverConfig = config.useSingleServer()
                    .setAddress("redis://" + host + ":" + port);
        }
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