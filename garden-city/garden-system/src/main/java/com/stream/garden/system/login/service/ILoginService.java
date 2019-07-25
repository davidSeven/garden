package com.stream.garden.system.login.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.system.user.model.User;

import java.util.Date;

/**
 * @author garden
 * @date 2019-07-24 17:21
 */
public interface ILoginService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     * @throws ApplicationException e
     */
    public User login(String username, String password) throws ApplicationException;

    /**
     * 修改最后登录信息
     *
     * @param userCode 用户编号
     * @param ip       ip
     * @param date     date
     * @throws ApplicationException e
     */
    public void updateLastLogin(String userCode, String ip, Date date) throws ApplicationException;
}
