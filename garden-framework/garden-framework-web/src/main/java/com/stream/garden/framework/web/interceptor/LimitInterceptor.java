package com.stream.garden.framework.web.interceptor;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.web.annotation.Limit;
import com.stream.garden.framework.web.enums.LimitType;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * 限流拦截器
 * <p>
 * 参考文档
 * <p>
 * 漏桶限流实现
 * https://www.cnblogs.com/carrychan/p/9435979.html
 * <p>
 * SpringCloud令牌桶限流
 * http://kailing.pub/article/index/arcid/251.html?tdsourcetag=s_pctim_aiomsg
 *
 * @author garden
 * @date 2019-07-15 9:08
 */
@Aspect
@Configuration
public class LimitInterceptor {
    /**
     * Remaining Rate Limit header name.
     */
    public static final String REMAINING_HEADER = "X-RateLimit-Remaining";
    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);
    private static final String UNKNOWN = "unknown";
    private final RedisTemplate<String, Object> limitRedisTemplate;
    /**
     * The name of the header that returns number of remaining requests during the current
     * second.
     */
    private String remainingHeader = REMAINING_HEADER;

    private Config defaultConfig;

    @Autowired
    public LimitInterceptor(RedisTemplate<String, Object> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;

        this.defaultConfig = new Config(2, 5);
    }

    static List<String> getKeys(String key) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + key;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    @Around("execution(public * *(..)) && @annotation(com.stream.garden.framework.web.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws ApplicationException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }

        // How many requests per second do you want a user to be allowed to do?
        int replenishRate = defaultConfig.getReplenishRate();

        // How much bursting do you want to allow?
        int burstCapacity = defaultConfig.getBurstCapacity();

        try {
            List<String> keys = getKeys(key);

            String luaScript = buildLuaScript();
            RedisScript<List<Long>> redisScript = new DefaultRedisScript<>(luaScript);

            // The arguments to the LUA script. time() returns unixtime in seconds.
            List<String> scriptArgs = Arrays.asList(replenishRate + "",
                    burstCapacity + "", Instant.now().getEpochSecond() + "", "1");
            List<Long> flux = this.limitRedisTemplate.execute(redisScript, keys, scriptArgs);

            if (null != flux) {
                return pjp.proceed();
            } else {
                throw new ApplicationException("You have been dragged into the blacklist");
            }
        } catch (Throwable e) {
            throw new ApplicationException("server exception");
        }
    }

    /**
     * 限流 脚本
     *
     * @return lua脚本
     */
    public String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        // 令牌桶
        // 当前限流的标识，可以是ip，或者在spring cloud系统中，可以是一个服务的serviceID
        lua.append("local tokens_key = KEYS[1]");
        // 令牌桶刷新的时间戳，后面会被用来计算当前产生的令牌数
        lua.append("\nlocal timestamp_key = KEYS[2]");

        // 令牌生产的速率，如每秒产生50个令牌
        lua.append("\nlocal rate = tonumber(ARGV[1])");
        // 令牌桶的容积大小，比如最大100个，那么系统最大可承载100个并发请求
        lua.append("\nlocal capacity = tonumber(ARGV[2])");

        // 当前时间戳
        lua.append("\nlocal now = tonumber(ARGV[3])");
        // 当前请求的令牌数量，Spring Cloud Gateway中默认是1，也就是当前请求
        lua.append("\nlocal requested = tonumber(ARGV[4])");

        lua.append("\nlocal fill_time = capacity/rate");
        lua.append("\nlocal ttl = math.floor(fill_time * 2)");

        lua.append("\nlocal last_tokens = tonumber(redis.call('get', tokens_key))");
        lua.append("\nif last_tokens == nil then");
        lua.append("\nlast_tokens = capacity");
        lua.append("\nend");

        lua.append("\nlocal last_refreshed = tonumber(redis.call('get', timestamp_key))");
        lua.append("\nif last_refreshed == nil then");
        lua.append("\nlast_refreshed = 0");
        lua.append("\nend");

        lua.append("\nlocal delta = math.max(0, now - last_refreshed)");
        lua.append("\nlocal filled_tokens = math.min(capacity, last_tokens + (delta * rate))");
        lua.append("\nlocal allowed = filled_tokens >= requested");
        lua.append("\nlocal new_tokens = filled_tokens");
        lua.append("\nlocal allowed_num = 0");
        lua.append("\nif allowed then");
        lua.append("\nnew_tokens = filled_tokens - requested");
        lua.append("\nallowed_num = 1");
        lua.append("\nend");

        lua.append("\nredis.call('setex', tokens_key, ttl, new_tokens)");
        lua.append("\nredis.call('setex', timestamp_key, ttl, now)");

        lua.append("\nreturn {allowed_num, new_tokens}");
        return lua.toString();
    }

    /**
     * 获取IP地址
     *
     * @return ip
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static class Config {

        private int replenishRate;

        private int burstCapacity = 1;

        public Config() {
        }

        public Config(int replenishRate, int burstCapacity) {
            this.replenishRate = replenishRate;
            this.burstCapacity = burstCapacity;
        }

        public int getReplenishRate() {
            return replenishRate;
        }

        public Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }

        public int getBurstCapacity() {
            return burstCapacity;
        }

        public Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }

        @Override
        public String toString() {
            return "Config{" + "replenishRate=" + replenishRate + ", burstCapacity="
                    + burstCapacity + '}';
        }

    }
}
