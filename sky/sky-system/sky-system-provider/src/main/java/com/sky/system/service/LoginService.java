package com.sky.system.service;

import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.api.dto.UserLoginDto;

/**
 * @date 2020-10-29 029 15:29
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param dto dto
     * @return UserLoginDto
     */
    UserLoginDto login(LoginDto dto);

    /**
     * 登出
     *
     * @param token token
     */
    void logout(String token);

    /**
     * 安全检查
     *
     * @param ip ip
     * @return SafetyCheckDto
     */
    SafetyCheckDto safetyCheck(String ip);
}
