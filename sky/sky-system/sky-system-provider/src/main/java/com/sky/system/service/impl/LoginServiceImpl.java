package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.interceptor.util.UUIDUtil;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.model.User;
import com.sky.system.service.LoginService;
import com.sky.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @date 2020-10-29 029 16:26
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserLoginDto login(LoginDto dto) {
        String vcToken = dto.getVcToken();
        if (StringUtils.isEmpty(vcToken)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        String vcKey = vcTokenPrefix(vcToken);
        Object vcValue = this.redisTemplate.opsForValue().get(vcKey);
        if (Boolean.TRUE.toString().equals(vcValue)) {
            if (StringUtils.isEmpty(dto.getVerifyCode())) {
                throw new CommonException(500, "用户名或密码错误");
            }
        }
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getCode, dto.getCode());
        User user = this.userService.getOne(queryWrapper);
        if (null == user) {
            throw new CommonException(500, "用户名或密码错误");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new CommonException(500, "用户未设置密码");
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new CommonException(500, "用户名或密码错误");
        }
        // 登录成功后删除vcToken
        this.redisTemplate.delete(vcKey);


        return null;
    }

    @Override
    public void logout(String token) {
        String key = liTokenPrefix(token);
        if (hasKey(key)) {
            this.redisTemplate.delete(key);
        }
    }

    private boolean hasKey(String key) {
        Boolean hasKey = this.redisTemplate.hasKey(key);
        if (null == hasKey) {
            hasKey = false;
        }
        return hasKey;
    }

    @Override
    public SafetyCheckDto safetyCheck(String ip) {
        SafetyCheckDto dto = new SafetyCheckDto();
        // 创建token
        String token = createToken();
        dto.setVcToken(token);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getLoginIp, ip);
        int count = this.userService.count(queryWrapper);
        if (count == 0) {
            dto.setNeedVc(false);
        } else {
            // 小于5个ip
            dto.setNeedVc(count <= 5);
        }
        String key = vcTokenPrefix(token);
        // 判断要不要验证码
        String value = "";
        if (dto.isNeedVc()) {
            value = Boolean.TRUE.toString();
        }
        // 3个小时
        this.redisTemplate.opsForValue().set(key, value, 3 * 60 * 60, TimeUnit.SECONDS);
        return dto;
    }

    private String createToken() {
        return UUIDUtil.uuid();
    }

    private String liTokenPrefix(String token) {
        return SystemInterface.SERVICE + ":li:" + token;
    }

    private String vcTokenPrefix(String token) {
        return SystemInterface.SERVICE + ":vc:" + token;
    }
}
