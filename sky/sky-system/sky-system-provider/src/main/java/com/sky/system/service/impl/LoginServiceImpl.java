package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.interceptor.util.UUIDUtil;
import com.sky.framework.utils.VerifyCodeUtils;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerifyCodeDto;
import com.sky.system.api.model.LoginVisitLog;
import com.sky.system.api.model.User;
import com.sky.system.listeners.LoginLogListener;
import com.sky.system.service.LoginService;
import com.sky.system.service.LoginVisitLogService;
import com.sky.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private LoginVisitLogService loginVisitLogService;

    @Override
    public UserLoginDto login(LoginDto dto) {
        String vcToken = dto.getVcToken();
        // 检测token不存在
        if (StringUtils.isEmpty(vcToken)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        // 检测数据不存在
        String vcKey = vcTokenPrefix(vcToken);
        VerifyCodeDto vcValue = (VerifyCodeDto) this.redisTemplate.opsForValue().get(vcKey);
        if (null == vcValue) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        // 判断是否需要验证码
        if (vcValue.isNeedVc()) {
            if (StringUtils.isEmpty(vcValue.getVerifyCode())) {
                throw new CommonException(500, "登录异常，请刷新后再操作");
            }
            if (StringUtils.isEmpty(dto.getVerifyCode())) {
                throw new CommonException(500, "验证码不能为空");
            }
            if (!vcValue.getVerifyCode().equals(dto.getVerifyCode())) {
                throw new CommonException(500, "验证码错误");
            }
        }
        // 查询用户信息
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
            // 记录登录失败次数
            User updateUser = new User();
            updateUser.setId(user.getId());
            Integer loginFailCount = user.getLoginFailCount();
            if (null == loginFailCount) {
                loginFailCount = 0;
            }
            loginFailCount++;
            updateUser.setLoginIp(dto.getIp());
            updateUser.setLoginDate(new Date());
            updateUser.setLoginFailCount(loginFailCount);
            this.userService.updateById(updateUser);
            LoginLogListener.loginFail(dto);
            throw new CommonException(500, "用户名或密码错误");
        }
        // 更新用户信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setLoginIp(dto.getIp());
        updateUser.setLoginDate(new Date());
        // 登录失败次数清零
        updateUser.setLoginFailCount(0);
        this.userService.updateById(updateUser);
        // 登录成功后删除vcToken
        this.redisTemplate.delete(vcKey);
        // 登录用户信息
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setId(user.getId());
        userLoginDto.setCode(user.getCode());
        userLoginDto.setName(user.getName());
        userLoginDto.setLoginIp(updateUser.getLoginIp());
        userLoginDto.setLoginDate(updateUser.getLoginDate());
        // 处理token
        String token = createToken();
        String liKey = liTokenPrefix(token);
        // 8个小时
        this.redisTemplate.opsForValue().set(liKey, userLoginDto, 8 * 60 * 60, TimeUnit.SECONDS);
        userLoginDto.setToken(token);
        LoginLogListener.login(dto, token);
        return userLoginDto;
    }

    @Override
    public void logout(String token, String ip) {
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
            dto.setNeedVc(true);
        } else {
            // 小于5个ip
            dto.setNeedVc(count <= 5);
        }
        String key = vcTokenPrefix(token);
        // 判断要不要验证码
        VerifyCodeDto verifyCodeDto = new VerifyCodeDto();
        verifyCodeDto.setNeedVc(dto.isNeedVc());
        // 3个小时
        this.redisTemplate.opsForValue().set(key, verifyCodeDto, 3 * 60 * 60, TimeUnit.SECONDS);
        // 增加访问日志
        LoginVisitLog visitLog = new LoginVisitLog();
        visitLog.setToken(token);
        visitLog.setIp(ip);
        visitLog.setNeedVerifyCode(dto.isNeedVc() ? 1 : 0);
        visitLog.setVerifyCode("");
        this.loginVisitLogService.addLog(visitLog);
        return dto;
    }

    @Override
    public String verifyCode(String ip, String verifyCodeToken) {
        String verifyCode = verifyCode(verifyCodeToken);
        // 更新访问日志
        this.loginVisitLogService.updateVerifyCode(verifyCodeToken, verifyCode);
        return verifyCode;
    }

    @Override
    public String verifyCodeResponse(String ip, String verifyCodeToken) {
        String verifyCode = verifyCode(verifyCodeToken);
        // 更新访问日志
        this.loginVisitLogService.updateVerifyCode(verifyCodeToken, verifyCode);
        return verifyCode;
    }

    private String verifyCode(String verifyCodeToken) {
        // 验证vct是否有效
        // 检测数据不存在
        String vcKey = vcTokenPrefix(verifyCodeToken);
        VerifyCodeDto vcValue = (VerifyCodeDto) this.redisTemplate.opsForValue().get(vcKey);
        if (null == vcValue) {
            // 操作异常
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        if (!vcValue.isNeedVc()) {
            // 操作异常
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        // 生成验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 将验证设置到缓存中
        vcValue.setVerifyCode(verifyCode);
        // 更新缓存
        this.redisTemplate.opsForValue().set(vcKey, vcValue);
        return verifyCode;
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
