package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import com.stream.garden.system.user.dao.IUserDao;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author garden
 * @date 2019-06-19 11:36
 */
@Service
public class UserServiceImpl extends AbstractBaseService<User, String> implements IUserService {

    public UserServiceImpl(IUserDao iUserDao) {
        super(iUserDao);
    }

    public IUserDao getDao() {
        return (IUserDao) super.baseMapper;
    }

    @Override
    public int insert(User user) throws ApplicationException {
        // 验证编码不能重复
        User paramUser = new User();
        paramUser.setCode(user.getCode());
        if (super.exists(paramUser)) {
            throw new ApplicationException(SystemExceptionCode.USER_CODE_REPEAT, user.getCode());
        }
        return super.insertSelective(user);
    }

    @Override
    public int update(User user) throws ApplicationException {
        // 验证编码不能重复
        User paramUser = new User();
        paramUser.setId(user.getId());
        paramUser.setCode(user.getCode());
        if (super.exists(paramUser)) {
            throw new ApplicationException(SystemExceptionCode.USER_CODE_REPEAT, user.getCode());
        }
        return super.updateSelective(user);
    }

    @Override
    public UserBO getByCode(String code) {
        return this.getDao().getByCode(code);
    }

    @Override
    public UserBO getById(String id) {
        return this.getDao().getById(id);
    }

    @Override
    public List<PermissionBO> getPermissionByRoleId(String roleId) {
        return this.getDao().getPermissionByRoleId(roleId);
    }
}
