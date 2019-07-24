package com.stream.garden.system.login.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.util.Md5SaltUtil;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangyuyuan
 * @date 2019-07-24 17:22
 */
@Service
public class LoginServiceImpl implements ILoginService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Override
    public User login(String username, String password) throws ApplicationException {
        try {
            User user = userService.getByName(username);
            if (Md5SaltUtil.validPassword(password, user.getPassword())) {
                return user;
            }
            // 累计失败次数
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }
}
