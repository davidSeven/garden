package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.LoginLog;

/**
 * @date 2020-11-05 005 14:28
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 检查
     *
     * @param loginLog loginLog
     */
    void safetyCheck(LoginLog loginLog);

    /**
     * 验证码
     *
     * @param loginLog loginLog
     */
    void verifyCode(LoginLog loginLog);

    /**
     * 登录
     *
     * @param loginLog loginLog
     */
    void login(LoginLog loginLog);

    /**
     * 登出
     *
     * @param loginLog loginLog
     */
    void logout(LoginLog loginLog);
}
