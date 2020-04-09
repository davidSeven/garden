package com.stream.garden.system.user.service;

import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import com.stream.garden.system.user.model.User;

import java.util.List;

/**
 * @author garden
 * @date 2019-06-19 11:27
 */
public interface IUserService extends IBaseService<User, String> {

    /**
     * 根据用户编码查询用户
     *
     * @param code 用户编码
     * @return UserBO
     */
    UserBO getByCode(String code);

    /**
     * 根据用户ID查询用户
     *
     * @param id id
     * @return UserBO
     */
    UserBO getById(String id);

    /**
     * 根据角色ID查询权限信息
     *
     * @param roleId roleId
     * @return list
     */
    List<PermissionBO> getPermissionByRoleId(String roleId);

    /**
     * 根据用户编码查询用户
     *
     * @param code code
     * @return User
     */
    User getUserByCode(String code);
}
