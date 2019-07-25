package com.stream.garden.system.user.service;

import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.user.model.User;

/**
 * @author garden
 * @date 2019-06-19 11:27
 */
public interface IUserService extends IBaseService<User, String> {

    /**
     * 根据用户编码查询用户
     *
     * @param code 用户编码
     * @return User
     */
    User getByCode(String code);
}
