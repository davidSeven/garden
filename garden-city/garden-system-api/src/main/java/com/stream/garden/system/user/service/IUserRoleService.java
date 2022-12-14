package com.stream.garden.system.user.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.user.model.UserRole;

/**
 * @author garden
 * @date 2019-06-22 11:00
 */
public interface IUserRoleService extends IBaseService<UserRole> {

    /**
     * 设置角色
     *
     * @param userRole 用户角色id
     * @return int
     * @throws ApplicationException e
     */
    Integer setRole(UserRole userRole) throws ApplicationException;
}
