package com.stream.garden.warehouse.config;

import com.stream.garden.warehouse.service.DistributedLocker;
import com.stream.garden.warehouse.service.impl.RedissonDistributedLocker;
import com.stream.garden.warehouse.util.RedissLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 参考文档，https://www.jianshu.com/p/c5048208709c
 *
 * @author garden
 * @date 2020-04-01 15:24
 */
@Configuration
public class RedissonAutoConfiguration {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private int port;
    @Value(value = "${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonSingle() {
        // redis://127.0.0.1:6379
        String address = "redis://" + host + ":" + port;
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(address);
        if (StringUtils.isNotBlank(password)) {
            serverConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     *
     * @return
     */
    @Bean
    public DistributedLocker distributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker();
        locker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(locker);
        return locker;
    }
}
