package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.role.dao.IRoleDao;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-06-22 11:14
 */
@Service
public class RoleService extends AbstractBaseService<Role, String> implements IRoleService {

    public RoleService(IRoleDao iRoleDao) {
        super(iRoleDao);
    }
}
