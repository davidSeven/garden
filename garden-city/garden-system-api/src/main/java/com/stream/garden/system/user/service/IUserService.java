package com.stream.garden.system.user.service;

import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.user.model.User;

/**
 * @author garden
 * @date 2019-06-19 11:27
 */
public interface IUserService extends IBaseService<User, String> {

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return User
     */
    User getByName(String name);
}
