package com.stream.garden.system.login.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.util.Md5SaltUtil;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.model.UserRole;
import com.stream.garden.system.user.service.IUserRoleService;
import com.stream.garden.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author garden
 * @date 2019-07-24 17:22
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public User login(String username, String password) throws ApplicationException {
        try {
            // 查询用户信息
            User user = userService.getByCode(username);
            // 没有查询到用户信息
            if (null == user) {
                return null;
            }
            // 验证密码
            if (Md5SaltUtil.validPassword(password, user.getPassword())) {
                // 查询角色信息
                UserRole userRoleParams = new UserRole();
                userRoleParams.setUserId(user.getId());
                List<UserRole> userRoleList = userRoleService.list(userRoleParams);
                if (CollectionUtil.isNotEmpty(userRoleList)) {
                    user.setCurrentRoleId(userRoleList.get(0).getRoleId());
                }
                // 成功
                return user;
            }
            // 用户被锁定
            if (SystemConstant.USER_STATE_LOCKED.equals(user.getState())) {
                return null;
            }
            // 登录失败，密码错误，累计失败次数
            Integer loginFailCount = user.getLoginFailCount();
            if (null == loginFailCount) {
                loginFailCount = 0;
            }
            loginFailCount++;
            User paramsUser = new User();
            paramsUser.setId(user.getId());
            paramsUser.setLoginFailCount(loginFailCount);
            if (loginFailCount > 3) {
                paramsUser.setState(SystemConstant.USER_STATE_LOCKED);
            }
            userService.updateSelective(paramsUser);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void updateLastLogin(String userId, String ip, Date date) throws ApplicationException {
        try {
            User paramsUser = new User();
            paramsUser.setId(userId);
            // 重置失败次数
            paramsUser.setLoginFailCount(0);
            paramsUser.setLastLoginIp(ip);
            paramsUser.setLastLoginDate(new Timestamp(date.getTime()));
            userService.updateSelective(paramsUser);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public User getByUserId(String userId) throws ApplicationException {
        try {
            return userService.get(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

}
