package com.stream.garden.framework.limit.core;

import com.stream.garden.framework.limit.annotation.Limit;
import com.stream.garden.framework.limit.enums.LimitType;
import com.stream.garden.framework.type.DefaultTargetType;
import com.stream.garden.framework.web.util.ApplicationUtil;
import com.stream.garden.framework.web.util.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * @author garden
 * @date 2019-07-20 9:45
 */
public abstract class AbstractHandlerLimit implements HandlerLimit {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RedisTemplate<String, Object> limitRedisTemplate;

    public AbstractHandlerLimit() {
        DefaultTargetType<RedisTemplate<String, Object>> targetType = new DefaultTargetType<RedisTemplate<String, Object>>() {
        };
        this.limitRedisTemplate = ApplicationUtil.getBean("stringObjectRedisTemplate", targetType.getClassType());
    }

    @Override
    public boolean handle(Limit limit) {
        boolean flag = true;
        try {
            int limitPeriod = limit.period();
            int limitCount = limit.count();
            List<String> keys = getKeys(limit);
            DefaultTargetType<List<Long>> targetType = new DefaultTargetType<List<Long>>() {
            };
            String luaScript = buildLuaScript();
            DefaultRedisScript<List<Long>> redisScript = new DefaultRedisScript<>(luaScript);
            redisScript.setResultType(targetType.getClassType());
            // The arguments to the LUA script. time() returns unixtime in seconds.
            Object[] scriptArgs = new Long[]{(long) limitPeriod,
                    (long) limitCount, Instant.now().getEpochSecond(), 1L};
            List<Long> flux = this.limitRedisTemplate.execute(redisScript, keys, scriptArgs);
            if (null != flux && 0L == flux.get(0)) {
                flag = false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return flag;
    }

    protected List<String> getKeys(Limit limitAnnotation) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster
        LimitType limitType = limitAnnotation.limitType();
        String key;
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = getIpAddress();
        }
        // Make a unique key per user.
        String annotationPrefix = "";
        if (StringUtils.isNotEmpty(limitAnnotation.name())) {
            annotationPrefix = limitAnnotation.name();
        }
        if (StringUtils.isNotEmpty(limitAnnotation.prefix())) {
            if (StringUtils.isNotEmpty(limitAnnotation.name())) {
                annotationPrefix += ":";
            }
            annotationPrefix += (limitAnnotation.prefix() + ":");
        }
        String prefix = annotationPrefix + "request_rate_limiter.{" + key;
        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    /**
     * 限流 脚本
     *
     * @return lua脚本
     */
    protected String buildLuaScript() {
        return "local tokens_key = KEYS[1]" +
                "\nlocal timestamp_key = KEYS[2]" +
                "\nlocal rate = tonumber(ARGV[1])" +
                "\nlocal capacity = tonumber(ARGV[2])" +
                "\nlocal now = tonumber(ARGV[3])" +
                "\nlocal requested = tonumber(ARGV[4])" +
                "\nlocal fill_time = capacity/rate" +
                "\nlocal ttl = math.floor(fill_time * 2)" +
                "\nlocal last_tokens = tonumber(redis.call('get', tokens_key))" +
                "\nif last_tokens == nil then" +
                "\nlast_tokens = capacity" +
                "\nend;" +
                "\nlocal last_refreshed = tonumber(redis.call('get', timestamp_key))" +
                "\nif last_refreshed == nil then" +
                "\nlast_refreshed = 0" +
                "\nend" +
                "\nlocal delta = math.max(0, now - last_refreshed)" +
                "\nlocal filled_tokens = math.min(capacity, last_tokens + (delta * rate))" +
                "\nlocal allowed = filled_tokens >= requested" +
                "\nlocal new_tokens = filled_tokens" +
                "\nlocal allowed_num = 0" +
                "\nif allowed then" +
                "\nnew_tokens = filled_tokens - requested" +
                "\nallowed_num = 1" +
                "\nend" +
                "\nredis.call('setex', tokens_key, ttl, new_tokens)" +
                "\nredis.call('setex', timestamp_key, ttl, now)" +
                "\nreturn {allowed_num, new_tokens}";
    }

    /**
     * 获取IP地址
     *
     * @return ip
     */
    private String getIpAddress() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return IPUtil.getIpAddress(servletRequestAttributes.getRequest());
        }
        return "";
    }
}
