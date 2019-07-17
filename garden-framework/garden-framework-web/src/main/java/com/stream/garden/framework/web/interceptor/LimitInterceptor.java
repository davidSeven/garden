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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);
    private static final String UNKNOWN = "unknown";
    private final RedisTemplate<String, Object> limitRedisTemplate;

    @Autowired
    public LimitInterceptor(RedisTemplate<String, Object> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }

    private List<String> getKeys(Limit limitAnnotation, String defaultKey) {
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
                key = defaultKey;
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

    @Around("execution(public * *(..)) && @annotation(com.stream.garden.framework.web.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws ApplicationException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();
        try {
            List<String> keys = getKeys(limitAnnotation, StringUtils.upperCase(method.getName()));
            DefaultTargetType<List<Long>> targetType = new DefaultTargetType<List<Long>>() {
            };
            String luaScript = buildLuaScript();
            DefaultRedisScript<List<Long>> redisScript = new DefaultRedisScript<>(luaScript);
            redisScript.setResultType(targetType.getClassType());
            // The arguments to the LUA script. time() returns unixtime in seconds.
            Object[] scriptArgs = new Long[]{(long) limitPeriod,
                    (long) limitCount, Instant.now().getEpochSecond(), 1L};
            List<Long> flux = this.limitRedisTemplate.execute(redisScript, keys, scriptArgs);
            if (null != flux && 1L == flux.get(0)) {
                return pjp.proceed();
            } else {
                throw new ApplicationException("请求过于频繁，请稍后再试");
            }
        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), e);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("server exception");
        }
    }

    /**
     * 限流 脚本
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
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
    private String getIpAddress() {
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

    public static class DefaultTargetType<T> {

        @SuppressWarnings("unchecked")
        DefaultTargetType() {
            Type superClass = getClass().getGenericSuperclass();
            this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            if (this.type instanceof ParameterizedType) {
                this.classType = (Class<T>) ((ParameterizedType) this.type).getRawType();
            } else {
                this.classType = (Class<T>) this.type;
            }
        }

        private Type type;
        private Class<T> classType;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Class<T> getClassType() {
            return classType;
        }

        public void setClassType(Class<T> classType) {
            this.classType = classType;
        }
    }
}
