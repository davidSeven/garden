package com.sky.system.service;

import com.sky.system.api.dto.*;

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
     * 登录信息
     *
     * @param token token
     * @return LoginInfoDto
     */
    LoginInfoDto info(String token);

    /**
     * 登出
     *
     * @param token token
     * @param ip    ip
     */
    void logout(String token, String ip);

    /**
     * 安全检查
     *
     * @param ip ip
     * @return SafetyCheckDto
     */
    SafetyCheckDto safetyCheck(String ip);

    /**
     * 验证码
     *
     * @param ip              ip
     * @param verifyCodeToken 验证码 token
     * @return 验证码
     */
    String verifyCode(String ip, String verifyCodeToken);

    /**
     * 验证码
     *
     * @param ip              ip
     * @param verifyCodeToken 验证码 token
     * @return 验证码
     */
    String verifyCodeResponse(String ip, String verifyCodeToken);

    /**
     * 验证
     *
     * @param dto dto
     * @return UserLoginDto
     */
    UserLoginDto verification(VerificationDto dto);
}
