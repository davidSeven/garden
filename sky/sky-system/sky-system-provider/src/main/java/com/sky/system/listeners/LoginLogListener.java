package com.sky.system.listeners;

import com.sky.framework.interceptor.util.ApplicationUtil;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.model.LoginLog;
import com.sky.system.api.model.OnlineUser;
import com.sky.system.constant.LoginLogConstant;
import com.sky.system.events.LoginLogEvent;
import com.sky.system.service.LoginLogService;
import com.sky.system.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @date 2020-11-05 005 14:31
 */
@Component
public class LoginLogListener implements ApplicationListener<LoginLogEvent> {

    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private OnlineUserService onlineUserService;

    public static void login(LoginDto dto, String token, Long userId, Long leaseTime) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_SUCCESS);
        copyLoginLog(loginLog, dto);
        loginLog.setLoginToken(token);
        publishEvent(loginLog, userId, leaseTime);
    }

    public static void loginFail(LoginDto dto) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_FAIL);
        copyLoginLog(loginLog, dto);
        publishEvent(loginLog, null, null);
    }

    private static void copyLoginLog(LoginLog loginLog, LoginDto dto) {
        loginLog.setLoginIp(dto.getIp());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginCode(dto.getCode());
        loginLog.setLoginPassword(dto.getPassword());
        loginLog.setLoginVerifyCodeToken(dto.getVcToken());
        loginLog.setLoginVerifyCode(dto.getVerifyCode());
    }

    private static void publishEvent(LoginLog loginLog, Long userId, Long leaseTime) {
        LoginLogEvent loginLogEvent = new LoginLogEvent(loginLog);
        loginLogEvent.setUserId(userId);
        loginLogEvent.setLeaseTime(leaseTime);
        ApplicationUtil.publishEvent(loginLogEvent);
    }

    @Async
    @Override
    public void onApplicationEvent(LoginLogEvent event) {
        Object source = event.getSource();
        if (null == source) {
            return;
        }
        LoginLog loginLog = (LoginLog) source;
        this.loginLogService.login(loginLog);
        if (LoginLogConstant.STATE_SUCCESS.equals(loginLog.getState())) {
            OnlineUser onlineUser = new OnlineUser();
            onlineUser.setUserId(event.getUserId());
            onlineUser.setUserCode(loginLog.getLoginCode());
            onlineUser.setLoginToken(loginLog.getLoginToken());
            onlineUser.setLoginIp(loginLog.getLoginIp());
            onlineUser.setLoginDate(loginLog.getLoginTime());
            onlineUser.setLastVisitDate(loginLog.getLoginTime());
            onlineUser.setLeaseTime(event.getLeaseTime());
            this.onlineUserService.addOnlineUser(onlineUser);
        }
    }
}
