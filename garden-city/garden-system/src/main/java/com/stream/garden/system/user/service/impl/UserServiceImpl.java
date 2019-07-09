package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.exception.SystemExceptionCode;
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

    @Override
    public int insert(User user) throws ApplicationException {
        // 验证编码不能重复
        User paramUser = new User();
        paramUser.setCode(user.getCode());
        if (super.exists(paramUser)) {
            throw new ApplicationException(SystemExceptionCode.USER_CODE_REPEAT, user.getCode());
        }
        return super.insert(user);
    }

    @Override
    public int update(User user) throws ApplicationException {
        // 验证编码不能重复
        User paramUser = new User();
        paramUser.setCode(user.getCode());
        if (super.baseMapper.exists(paramUser) > 1) {
            throw new ApplicationException(SystemExceptionCode.USER_CODE_REPEAT, user.getCode());
        }
        return super.update(user);
    }
}
