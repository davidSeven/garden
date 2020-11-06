package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.LoginLog;
import com.sky.system.dao.LoginLogDao;
import com.sky.system.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * @date 2020-11-05 005 14:29
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLog> implements LoginLogService {

    @Override
    public void login(LoginLog loginLog) {
        this.save(loginLog);
    }

}
