package com.stream.garden.framework.cache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

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
            RedisScript<String> redisScript = new DefaultRedisScript<>(getLockScripts, String.class);
            Object result = stringObjectRedisTemplate.execute(redisScript, Collections.singletonList(key), value, expireTime);
            if (SUCCESS.equals(result)) {
                lock = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return lock;
    }

    /**
     * 释放锁
     *
     * @param key   key
     * @param value value
     * @return boolean
     */
    public static boolean releaseLock(String key, String value) {
        RedisScript<String> redisScript = new DefaultRedisScript<>(releaseLockScripts, String.class);
        Object result = stringObjectRedisTemplate.execute(redisScript, Collections.singletonList(key), value);
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
        boolean lock = false;
        Timer waitTimer = null;
        try {
            if (-1 == waitTime) {
                do {
                    if (getLock(key, value, expireTime)) {
                        return true;
                    }
                    Thread.sleep(1);
                } while (true);
            } else {
                final boolean[] endWhile = {true};
                waitTimer = new Timer();
                waitTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        endWhile[0] = false;
                    }
                }, waitTime);
                do {
                    if (getLock(key, value, expireTime)) {
                        return true;
                    }
                    Thread.sleep(1);
                } while (endWhile[0]);
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != waitTimer) {
                waitTimer.cancel();
            }
        }
        return lock;
    }


    @SuppressWarnings({"unchecked"})
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // redis init
        RedisLockUtil.stringObjectRedisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("stringObjectRedisTemplate");
        // redis lock scripts init
        Properties readProperties = PropertiesUtil.readProperties(REDIS_LOCK_PROPERTIES);
        if (null == readProperties) {
            throw new NullPointerException("redis lock scripts is null, redis properties path:" + REDIS_LOCK_PROPERTIES);
        } else {
            RedisLockUtil.getLockScripts = readProperties.getProperty("scripts.getLock");
            RedisLockUtil.releaseLockScripts = readProperties.getProperty("scripts.releaseLock");
        }
    }
}
