package com.forest.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forest.framework.common.exception.CommonException;
import com.forest.framework.utils.BeanHelpUtil;
import com.forest.framework.utils.UUIDUtil;
import com.forest.system.ForestInterface;
import com.forest.system.dto.LoginDto;
import com.forest.system.dto.UserDto;
import com.forest.system.model.User;
import com.forest.system.service.LoginService;
import com.forest.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @date 2020-10-19 019 13:49
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 前缀
     */
    protected static final String PREFIX = ForestInterface.SERVICE_NAME + ":login:";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserService userService;

    @Override
    public String login(LoginDto loginDto) {
        // 查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", loginDto.getAccount());
        User user = this.userService.getOne(queryWrapper);
        if (null == user) {
            throw new CommonException("500", "帐号或密码错误");
        }
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new CommonException("500", "帐号或密码错误");
        }
        // 处理token
        String token = UUIDUtil.uuid();
        String wrapperToken = this.wrapperPrefix(token);
        this.redisTemplate.opsForValue().set(wrapperToken, user, 2 * 60 * 60, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public void logout(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        String wrapperToken = this.wrapperPrefix(token);
        // 处理redis中的值
        if (this.hasKey(wrapperToken)) {
            this.redisTemplate.delete(wrapperToken);
        }
    }

    private boolean hasKey(String wrapperToken) {
        Boolean hasKey = this.redisTemplate.hasKey(wrapperToken);
        if (null == hasKey) {
            hasKey = false;
        }
        return hasKey;
    }

    @Override
    public UserDto valid(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String wrapperToken = this.wrapperPrefix(token);
        if (this.hasKey(wrapperToken)) {
            Object o = this.redisTemplate.opsForValue().get(wrapperToken);
            if (null != o) {
                User user = (User) o;
                return BeanHelpUtil.convertDto(user, UserDto.class);
            }
        }
        return null;
    }

    private String wrapperPrefix(String token) {
        return PREFIX + token;
    }
}
