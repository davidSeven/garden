package com.forest.system.service;

import com.forest.system.dto.LoginDto;
import com.forest.system.dto.UserDto;

/**
 * @date 2020-10-19 019 13:45
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginDto loginDto
     * @return token
     */
    String login(LoginDto loginDto);

    /**
     * 登出
     *
     * @param token token
     */
    void logout(String token);

    /**
     * 验证
     *
     * @param token token
     * @return UserDto
     */
    UserDto valid(String token);
}
