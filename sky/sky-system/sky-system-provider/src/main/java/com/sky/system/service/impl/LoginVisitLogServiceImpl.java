package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.LoginVisitLog;
import com.sky.system.dao.LoginVisitLogDao;
import com.sky.system.service.LoginVisitLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @date 2020-12-14 014 13:42
 */
@Service
public class LoginVisitLogServiceImpl extends ServiceImpl<LoginVisitLogDao, LoginVisitLog> implements LoginVisitLogService {

    @Async
    @Override
    public void addLog(LoginVisitLog visitLog) {
        // 保存访问日志
        this.save(visitLog);
    }

    @Async
    @Override
    public void updateVerifyCode(String token, String verifyCode) {
        LambdaUpdateWrapper<LoginVisitLog> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(LoginVisitLog::getVerifyCode, verifyCode);
        updateWrapper.eq(LoginVisitLog::getToken, token);
        this.update(updateWrapper);
    }
}
