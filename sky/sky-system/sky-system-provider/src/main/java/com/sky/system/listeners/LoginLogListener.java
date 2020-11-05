package com.sky.system.listeners;

import com.sky.framework.interceptor.util.ApplicationUtil;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.dto.SafetyCheckDto;
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

    public static void safetyCheck(String ip, SafetyCheckDto dto) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_SAFETY_CHECK);
        loginLog.setSafetyCheckIp(ip);
        loginLog.setSafetyCheckTime(new Date());
        loginLog.setSafetyCheckValue(String.valueOf(dto.isNeedVc()));
        loginLog.setSafetyCheckToken(dto.getVcToken());
        publishEvent(loginLog);
    }

    public static void verifyCode(String ip, String verifyCode, String verifyCodeToken, String type) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_VERIFY_CODE);
        loginLog.setVerifyCodeIp(ip);
        loginLog.setVerifyCodeTime(new Date());
        loginLog.setVerifyCodeValue(verifyCode);
        loginLog.setVerifyCodeType(type);
        loginLog.setVerifyCodeToken(verifyCodeToken);
        publishEvent(loginLog);
    }

    public static void login(LoginDto dto, String token) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_LOGIN);
        copyLoginLog(loginLog, dto);
        loginLog.setLoginResult(LoginLogConstant.LOGIN_RESULT_SUCCESS);
        loginLog.setLoginToken(token);
        publishEvent(loginLog);
    }

    public static void loginFail(LoginDto dto) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_LOGIN);
        copyLoginLog(loginLog, dto);
        loginLog.setLoginResult(LoginLogConstant.LOGIN_RESULT_FAIL);
        publishEvent(loginLog);
    }

    private static void copyLoginLog(LoginLog loginLog, LoginDto dto) {
        loginLog.setLoginIp(dto.getIp());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginCode(dto.getCode());
        loginLog.setLoginPassword(dto.getPassword());
        loginLog.setLoginVerifyCode(dto.getVerifyCode());
        loginLog.setLoginSafetyCheckToken(dto.getVcToken());
    }

    public static void logout(String token, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setState(LoginLogConstant.STATE_LOGOUT);
        loginLog.setLoginToken(token);
        loginLog.setLogoutIp(ip);
        loginLog.setLogoutTime(new Date());
        publishEvent(loginLog);
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
        if (LoginLogConstant.STATE_SAFETY_CHECK.equals(loginLog.getState())) {
            this.loginLogService.safetyCheck(loginLog);
        } else if (LoginLogConstant.STATE_VERIFY_CODE.equals(loginLog.getState())) {
            this.loginLogService.verifyCode(loginLog);
        } else if (LoginLogConstant.STATE_LOGIN.equals(loginLog.getState())) {
            this.loginLogService.login(loginLog);
        } else if (LoginLogConstant.STATE_LOGOUT.equals(loginLog.getState())) {
            this.loginLogService.logout(loginLog);
        }
    }
}
