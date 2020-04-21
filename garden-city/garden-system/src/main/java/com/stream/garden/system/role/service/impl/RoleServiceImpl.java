package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.role.dao.IRoleDao;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-06-22 11:14
 */
@Service
public class RoleServiceImpl extends AbstractBaseService<Role, IRoleDao> implements IRoleService {

    @Override
    public int insert(Role role) throws ApplicationException {
        // 验证编码不能重复
        Role paramRole = new Role();
        paramRole.setCode(role.getCode());
        if (super.exists(paramRole)) {
            throw new ApplicationException(SystemExceptionCode.ROLE_CODE_REPEAT, role.getCode());
        }
        return super.insert(role);
    }

    @Override
    public int update(Role role) throws ApplicationException {
        // 验证编码不能重复
        Role paramRole = new Role();
        paramRole.setId(role.getId());
        paramRole.setCode(role.getCode());
        if (super.exists(paramRole)) {
            throw new ApplicationException(SystemExceptionCode.ROLE_CODE_REPEAT, role.getCode());
        }
        return super.update(role);
    }

    @Override
    public int delete(Serializable... ids) throws ApplicationException {
        // 1.删除与用户的关系
        // 2.删除与群组的关系
        // 3.删除权限的关系
        return super.delete(ids);
    }
}
