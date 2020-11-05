package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.LoginLog;
import com.sky.system.constant.LoginLogConstant;
import com.sky.system.dao.LoginLogDao;
import com.sky.system.service.LoginLogService;
import com.sky.system.service.LoginLogTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2020-11-05 005 14:29
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLog> implements LoginLogService {

    @Autowired
    private LoginLogTokenService loginLogTokenService;

    @Override
    public void safetyCheck(LoginLog loginLog) {
        this.save(loginLog);
        this.loginLogTokenService.create(loginLog.getSafetyCheckToken(), loginLog.getId());
    }

    @Override
    public void verifyCode(LoginLog loginLog) {
        Long logId = this.loginLogTokenService.getBySafetyCheckToken(loginLog.getVerifyCodeToken());
        LoginLog record = this.getById(logId);
        if (null == record) {
            return;
        }
        long count = 0;
        if (null != record.getVerifyCodeCount()) {
            count = record.getVerifyCodeCount();
        }
        LoginLog update = new LoginLog();
        update.setId(logId);
        update.setState(loginLog.getState());
        update.setVerifyCodeIp(loginLog.getVerifyCodeIp());
        update.setVerifyCodeTime(loginLog.getVerifyCodeTime());
        update.setVerifyCodeValue(loginLog.getVerifyCodeValue());
        update.setVerifyCodeType(loginLog.getVerifyCodeType());
        count++;
        update.setVerifyCodeCount(count);
        update.setVerifyCodeToken(loginLog.getVerifyCodeToken());
        this.updateById(update);
    }

    @Override
    public void login(LoginLog loginLog) {
        Long logId = this.loginLogTokenService.getBySafetyCheckToken(loginLog.getLoginSafetyCheckToken());
        LoginLog record = this.getById(logId);
        if (null == record) {
            return;
        }
        long count = 0;
        if (null != record.getLoginCount()) {
            count = record.getLoginCount();
        }
        LoginLog update = new LoginLog();
        update.setId(logId);
        update.setState(loginLog.getState());
        update.setLoginIp(loginLog.getLoginIp());
        update.setLoginTime(loginLog.getLoginTime());
        update.setLoginCode(loginLog.getLoginCode());
        update.setLoginPassword(loginLog.getLoginPassword());
        update.setLoginVerifyCode(loginLog.getLoginVerifyCode());
        update.setLoginSafetyCheckToken(loginLog.getLoginSafetyCheckToken());
        count++;
        update.setLoginCount(count);
        update.setLoginResult(loginLog.getLoginResult());
        update.setLoginToken(loginLog.getLoginToken());
        if (LoginLogConstant.LOGIN_RESULT_FAIL.equals(loginLog.getLoginResult())) {
            long failCount = 0;
            if (null != record.getLoginFailCount()) {
                failCount = record.getLoginFailCount();
            }
            failCount++;
            update.setLoginFailCount(failCount);
        }
        this.updateById(update);
        this.loginLogTokenService.changeToken(loginLog.getLoginSafetyCheckToken(), loginLog.getLoginToken());
    }

    @Override
    public void logout(LoginLog loginLog) {
        Long logId = this.loginLogTokenService.getByLoginToken(loginLog.getLoginToken());
        LoginLog record = this.getById(logId);
        if (null == record) {
            return;
        }
        LoginLog update = new LoginLog();
        update.setId(logId);
        update.setState(loginLog.getState());
        update.setLogoutIp(loginLog.getLogoutIp());
        update.setLogoutTime(loginLog.getLogoutTime());
        this.updateById(update);
    }
}
