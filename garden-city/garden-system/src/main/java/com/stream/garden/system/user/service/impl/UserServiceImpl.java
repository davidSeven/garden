package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.vo.BasePageVO;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.Md5SaltUtil;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import com.stream.garden.system.user.dao.IUserDao;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author garden
 * @date 2019-06-19 11:36
 */
@Service
public class UserServiceImpl extends AbstractBaseService<User, String, IUserDao> implements IUserService {

    @Override
    public int insert(User user) throws ApplicationException {
        // 验证编码不能重复
        User paramUser = new User();
        paramUser.setCode(user.getCode());
        if (super.exists(paramUser)) {
            throw new ApplicationException(SystemExceptionCode.USER_CODE_REPEAT, user.getCode());
        }
        // 密码加密
        encryptedPwd(user);
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
        // 密码加密
        encryptedPwd(user);
        return super.updateSelective(user);
    }

    /**
     * 密码加密
     *
     * @param user user
     */
    private void encryptedPwd(User user) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            try {
                user.setPassword(Md5SaltUtil.getEncryptedPwd(user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public UserBO getByCode(String code) {
        return this.getMapper().getByCode(code);
    }

    @Override
    public UserBO getById(String id) {
        return this.getMapper().getById(id);
    }

    @Override
    public List<PermissionBO> getPermissionByRoleId(String roleId) {
        return this.getMapper().getPermissionByRoleId(roleId);
    }

    @Override
    public PageInfo<User> pageList(BasePageVO<User, String> pageVO) throws ApplicationException {
        return super.pageList(pageVO);
    }

    @Override
    public User getUserByCode(String code) {
        return this.getMapper().getUserByCode(code);
    }
}
