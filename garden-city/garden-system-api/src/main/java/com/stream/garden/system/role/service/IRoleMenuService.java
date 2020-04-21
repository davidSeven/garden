package com.stream.garden.system.role.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.role.model.RoleMenu;

/**
 * @author garden
 * @date 2019-06-22 11:08
 */
public interface IRoleMenuService extends IBaseService<RoleMenu> {

    int deleteByRoleId(String roleId) throws ApplicationException;
}
