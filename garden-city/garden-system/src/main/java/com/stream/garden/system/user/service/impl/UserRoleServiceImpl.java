package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
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
public class UserRoleServiceImpl extends AbstractBaseService<UserRole, IUserRoleDao> implements IUserRoleService {

    @Override
    public Integer setRole(UserRole userRole) throws ApplicationException {
        // 先删除关系
        this.getMapper().delete(userRole.getUserId());
        return super.insert(userRole);
    }
}
