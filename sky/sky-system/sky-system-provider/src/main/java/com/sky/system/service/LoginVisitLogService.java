package com.sky.system.service;

import com.sky.system.api.model.LoginVisitLog;

/**
 * @date 2020-12-14 014 13:42
 */
public interface LoginVisitLogService {

    /**
     * 新增访问日志
     *
     * @param visitLog visitLog
     */
    void addLog(LoginVisitLog visitLog);

    /**
     * 更新验证码
     *
     * @param token      token
     * @param verifyCode verifyCode
     */
    void updateVerifyCode(String token, String verifyCode);
}
