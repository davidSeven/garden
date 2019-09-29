package com.stream.garden.system.login.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.util.Md5SaltUtil;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.login.util.EncryptCacheUtil;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleService;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.model.UserRole;
import com.stream.garden.system.user.service.IUserRoleService;
import com.stream.garden.system.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private IRoleService roleService;

    @Override
    public UserBO login(String username, String password) throws ApplicationException {
        try {
            /*
             * 验证登录信息
             * 1.查询用户信息
             * 2.验证登录密码信息
             *
             * 验证用户，角色信息
             * 1.验证用户角色信息，判断角色是否可用
             * 2.登录成功
             * 3.登录失败，记录失败次数。如果次数大于3次，就锁定用户
             */
            // 查询用户信息
            UserBO userBO = userService.getByCode(username);
            // 没有查询到用户信息
            if (null == userBO) {
                return null;
            }
            // 密码解析
            String decryptPassword = EncryptCacheUtil.decryptRsaPassword(password);
            // 验证密码
            if (Md5SaltUtil.validPassword(decryptPassword, userBO.getPassword())) {
                // 如果当前角色信息不存在
                if (StringUtils.isEmpty(userBO.getCurrentRoleId())) {
                    // 获取当前用户角色信息
                    Role currentUserRole = getCurrentUserRole(userBO.getId());
                    if (null == currentUserRole) {
                        // 没有获取到用户的角色信息
                        return null;
                    } else {
                        // 修改当前用户的角色
                        User updateUser = new User();
                        updateUser.setId(userBO.getId());
                        updateUser.setCurrentRoleId(currentUserRole.getId());
                        userService.updateSelective(updateUser);
                        // 设置当前角色id
                        userBO.setCurrentRoleId(currentUserRole.getId());
                    }
                } else {
                    // 判断当前角色是否可用
                    if (!SystemConstant.STATE_1.equals(userBO.getRoleState())) {
                        // 当前角色不可用，不允许登录
                        return null;
                    }
                }
                // 成功
                return userBO;
            } else {
                // 登录失败
                loginFail(userBO);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * 获取用户角色信息
     *
     * @param userId userId
     * @return Role
     */
    private Role getCurrentUserRole(String userId) {
        Role currentUserRole = null;
        try {
            // 验证角色是否可用
            UserRole userRoleParams = new UserRole();
            userRoleParams.setUserId(userId);
            List<UserRole> userRoleList = userRoleService.list(userRoleParams);
            if (CollectionUtil.isNotEmpty(userRoleList)) {
                for (UserRole userRole : userRoleList) {
                    // 查询对应的角色信息
                    Role role = roleService.get(userRole.getRoleId());
                    // 判断角色是否启用
                    if (SystemConstant.STATE_1.equals(role.getState())) {
                        currentUserRole = role;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return currentUserRole;
    }

    /**
     * 记录登录失败信息
     *
     * @param user user
     * @throws ApplicationException e
     */
    private void loginFail(User user) throws ApplicationException {
        try {
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
            this.userService.updateSelective(paramsUser);
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
    public UserBO getByUserId(String userId) throws ApplicationException {
        try {
            return userService.getById(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @Override
    public List<PermissionBO> getPermissionByRoleId(String roleId) throws ApplicationException {
        try {
            return userService.getPermissionByRoleId(roleId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
