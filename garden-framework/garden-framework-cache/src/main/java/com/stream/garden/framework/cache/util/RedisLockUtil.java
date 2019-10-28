package com.stream.garden.framework.cache.util;

import com.stream.garden.framework.type.DefaultTargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * redis 锁
 *
 * @author gardne
 * @date 2019-10-16 9:43
 */
@Component
public class RedisLockUtil implements ApplicationContextAware {
    /**
     * redis properties
     */
    private static final String REDIS_LOCK_PROPERTIES = "redisLock.properties";
    private static final Long SUCCESS = 1L;
    /**
     * 获取锁脚本
     */
    private static String getLockScripts = "";
    /**
     * 释放锁脚本
     */
    private static String releaseLockScripts = "";
    private static Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);
    private static RedisTemplate<String, Object> stringObjectRedisTemplate;

    /**
     * 获取锁
     *
     * @param key        key
     * @param value      value
     * @param expireTime 有效时间-单位：秒
     * @return boolean
     */
    public static boolean getLock(String key, String value, int expireTime) {
        boolean lock = false;
        try {
            RedisScript<List<Long>> redisScript = new DefaultRedisScript<>(getLockScripts, new DefaultTargetType<List<Long>>() {
            }.getClassType());
            List<String> keys = buildKey(key);
            List<Long> result = stringObjectRedisTemplate.execute(redisScript, keys, value, expireTime);
            if (null != result && SUCCESS.equals(result.get(0))) {
                lock = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return lock;
    }

    /**
     * 构建keys
     *
     * @param args args
     * @param <T>  T
     * @return List
     */
    @SafeVarargs
    private static <T> List<T> buildKey(T... args) {
        return new ArrayList<>(Arrays.asList(args));
    }

    /**
     * 释放锁
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    public static boolean releaseLock(String key, String value) {
        RedisScript<List<Long>> redisScript = new DefaultRedisScript<>(releaseLockScripts, new DefaultTargetType<List<Long>>() {
        }.getClassType());
        List<String> keys = buildKey(key);
        Object result = stringObjectRedisTemplate.execute(redisScript, keys, value);
        return SUCCESS.equals(result);
    }

    /**
     * 获取自旋锁
     *
     * @param key        key
     * @param value      value
     * @param expireTime 有效时间-单位：秒
     * @param waitTime   等待时间-单位：毫秒
     * @return boolean
     */
    public static boolean getSpinLock(String key, String value, int expireTime, int waitTime) {
        long current = System.currentTimeMillis();
        try {
            // 第一次获取锁
            if (getLock(key, value, expireTime)) {
                // 获取成功
                return true;
            } else {
                // 循环获取锁
                return getSpinWhileLock((long) waitTime, current, key, value, expireTime);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 循环获取锁
     *
     * @param time       time
     * @param current    current
     * @param key        key
     * @param value      value
     * @param expireTime expireTime
     * @return boolean
     */
    private static boolean getSpinWhileLock(long time, long current, String key, String value, int expireTime) {
        try {
            // waitTime - 已经执行的时间
            time = time - (System.currentTimeMillis() - current);
            // 已经超时
            if (time < 0L) {
                // 获取失败
                return false;
            } else {
                do {
                    long currentTime = System.currentTimeMillis();
                    // 尝试获取
                    if (getLock(key, value, expireTime)) {
                        return true;
                    }
                    time -= System.currentTimeMillis() - currentTime;
                    currentTime = System.currentTimeMillis();
                    if (time < 0L) {
                        return false;
                    }
                    // 线程等待
                    Thread.sleep(1L);
                    time -= System.currentTimeMillis() - currentTime;
                } while (time > 0L);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 超过等待时间，返回失败
        return false;
    }

    /**
     * 设置时间
     *
     * @param key      key
     * @param timeout  timeout
     * @param timeUnit timeUnit
     * @return boolean
     */
    public static boolean expire(String key, long timeout, TimeUnit timeUnit) {
        try {
            Boolean value = stringObjectRedisTemplate.expire(key, timeout, timeUnit);
            if (null == value) {
                return false;
            }
            return value;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private static void setStringObjectRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisLockUtil.stringObjectRedisTemplate = redisTemplate;
    }

    private static void setGetLockScripts(String scripts) {
        RedisLockUtil.getLockScripts = scripts;
    }

    private static void setReleaseLockScripts(String scripts) {
        RedisLockUtil.releaseLockScripts = scripts;
    }

    /**
     * 获取id
     *
     * @param prefix 前缀
     * @return string
     */
    public String getNextId(String prefix) {
        String key = "ID_" + prefix;
        String orderId = null;
        try {
            Long increment = stringObjectRedisTemplate.opsForValue().increment(key, 1);
            // 往前补6位
            orderId = prefix + String.format("%1$06d", increment);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return orderId;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        try {
            // redis init
            setStringObjectRedisTemplate((RedisTemplate<String, Object>) applicationContext.getBean("stringObjectRedisTemplate"));
            // redis lock scripts init
            Properties readProperties = PropertiesUtil.readProperties(REDIS_LOCK_PROPERTIES);
            if (null == readProperties) {
                throw new NullPointerException("redis lock scripts is null, redis properties path:" + REDIS_LOCK_PROPERTIES);
            } else {
                setGetLockScripts(readProperties.getProperty("scripts.getLock"));
                setReleaseLockScripts(readProperties.getProperty("scripts.releaseLock"));
            }
        } catch (Exception e) {
            throw new BeanInitializationException(e.getMessage(), e);
        }
    }
}
