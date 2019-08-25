package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.role.dao.IRoleGroupDao;
import com.stream.garden.system.role.model.RoleGroup;
import com.stream.garden.system.role.service.IRoleGroupService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-06-22 11:15
 */
@Service
public class RoleGroupServiceImpl extends AbstractBaseService<RoleGroup, String> implements IRoleGroupService {

    public RoleGroupServiceImpl(IRoleGroupDao iRoleGroupDao) {
        super(iRoleGroupDao);
    }
}
