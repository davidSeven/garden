package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.LoginLogToken;
import com.sky.system.dao.LoginLogTokenDao;
import com.sky.system.service.LoginLogTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2020-11-05 005 14:28
 */
@Service
public class LoginLogTokenServiceImpl extends ServiceImpl<LoginLogTokenDao, LoginLogToken> implements LoginLogTokenService {

    @Transactional
    @Override
    public void create(String safetyCheckToken, Long logId) {
        LoginLogToken loginLogToken = new LoginLogToken();
        loginLogToken.setSafetyCheckToken(safetyCheckToken);
        loginLogToken.setLogId(logId);
        this.save(loginLogToken);
    }

    @Transactional
    @Override
    public void changeToken(String safetyCheckToken, String loginToken) {
        LambdaUpdateWrapper<LoginLogToken> updateWrapper = Wrappers.lambdaUpdate(LoginLogToken.class);
        updateWrapper.set(LoginLogToken::getLoginToken, loginToken);
        updateWrapper.eq(LoginLogToken::getSafetyCheckToken, safetyCheckToken);
        this.update(updateWrapper);
    }

    @Override
    public Long getBySafetyCheckToken(String safetyCheckToken) {
        LambdaQueryWrapper<LoginLogToken> queryWrapper = Wrappers.lambdaQuery(LoginLogToken.class);
        queryWrapper.eq(LoginLogToken::getSafetyCheckToken, safetyCheckToken);
        LoginLogToken loginLogToken = this.getOne(queryWrapper);
        if (null != loginLogToken) {
            return loginLogToken.getLogId();
        }
        return null;
    }

    @Override
    public Long getByLoginToken(String loginToken) {
        LambdaQueryWrapper<LoginLogToken> queryWrapper = Wrappers.lambdaQuery(LoginLogToken.class);
        queryWrapper.eq(LoginLogToken::getLoginToken, loginToken);
        LoginLogToken loginLogToken = this.getOne(queryWrapper);
        if (null != loginLogToken) {
            return loginLogToken.getLogId();
        }
        return null;
    }
}
