package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.user.dao.IUserRoleDao;
import com.stream.garden.system.user.model.UserRole;
import com.stream.garden.system.user.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-06-22 11:09
 */
@Service
public class UserRoleServiceImpl extends AbstractBaseService<UserRole, String> implements IUserRoleService {

    public UserRoleServiceImpl(IUserRoleDao iUserRoleDao) {
        super(iUserRoleDao);
    }
}
