package com.stream.garden.system.login.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;

import java.util.Date;
import java.util.List;

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
     * @return UserBO
     * @throws ApplicationException e
     */
    UserBO login(String username, String password) throws ApplicationException;

    /**
     * 修改最后登录信息
     *
     * @param userCode 用户编号
     * @param ip       ip
     * @param date     date
     * @throws ApplicationException e
     */
    void updateLastLogin(String userCode, String ip, Date date) throws ApplicationException;

    /**
     * 查询用户信息
     *
     * @param userId userId
     * @return User
     * @throws ApplicationException e
     */
    UserBO getByUserId(String userId) throws ApplicationException;

    /**
     * 根据角色ID查询权限信息
     *
     * @param roleId roleId
     * @return list
     * @throws ApplicationException e
     */
    List<PermissionBO> getPermissionByRoleId(String roleId) throws ApplicationException;
}
