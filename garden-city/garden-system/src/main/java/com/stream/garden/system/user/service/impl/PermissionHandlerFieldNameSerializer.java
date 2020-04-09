package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.web.interceptor.HandlerFieldNameSerializer;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2020-04-09 9:24
 */
@Component
public class PermissionHandlerFieldNameSerializer implements HandlerFieldNameSerializer {

    /**
     * 缓存KEY
     */
    private static final String PERMISSION_HANDLER_FIELD_NAME = "garden:permissionHandlerFieldName:";
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getName(String code) {
        if (StringUtils.isNotEmpty(code)) {
            String key = PERMISSION_HANDLER_FIELD_NAME + code;
            // 从缓存中获取
            Boolean hasKey = redisTemplate.hasKey(key);
            if (null != hasKey && hasKey) {
                Object value = redisTemplate.opsForValue().get(key);
                return (String) value;
            } else {
                String value = "";
                User user = null;
                try {
                    user = this.userService.get(code);
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
                if (null != user) {
                    value = user.getName();
                }
                redisTemplate.opsForValue().set(key, value, 10, TimeUnit.DAYS);
                return value;
            }
        }
        return null;
    }
}
