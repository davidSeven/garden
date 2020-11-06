package com.sky.system.listeners;

import com.sky.framework.interceptor.util.ApplicationUtil;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.model.LoginLog;
import com.sky.system.constant.LoginLogConstant;
import com.sky.system.events.LoginLogEvent;
import com.sky.system.service.LoginLogService;
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

    public static void login(LoginDto dto, String token) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_SUCCESS);
        copyLoginLog(loginLog, dto);
        loginLog.setLoginToken(token);
        publishEvent(loginLog);
    }

    public static void loginFail(LoginDto dto) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_FAIL);
        copyLoginLog(loginLog, dto);
        publishEvent(loginLog);
    }

    private static void copyLoginLog(LoginLog loginLog, LoginDto dto) {
        loginLog.setLoginIp(dto.getIp());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginCode(dto.getCode());
        loginLog.setLoginPassword(dto.getPassword());
        loginLog.setLoginVerifyCode(dto.getVerifyCode());
    }

    private static void publishEvent(LoginLog loginLog) {
        LoginLogEvent loginLogEvent = new LoginLogEvent(loginLog);
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
    }
}
