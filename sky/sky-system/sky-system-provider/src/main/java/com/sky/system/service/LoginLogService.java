package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.LoginLog;

/**
 * @date 2020-11-05 005 14:28
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 登录
     *
     * @param loginLog loginLog
     */
    void login(LoginLog loginLog);

}
