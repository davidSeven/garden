package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.role.dao.IRoleMenuDao;
import com.stream.garden.system.role.model.RoleMenu;
import com.stream.garden.system.role.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019/8/25 20:48
 */
@Service
public class RoleMenuServiceImpl extends AbstractBaseService<RoleMenu, String, IRoleMenuDao> implements IRoleMenuService {

    @Override
    public int deleteByRoleId(String roleId) throws ApplicationException {
        return this.getMapper().deleteByRoleId(new RoleMenu(roleId));
    }
}
