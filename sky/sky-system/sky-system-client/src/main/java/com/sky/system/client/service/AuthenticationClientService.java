package com.sky.system.client.service;

import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;

/**
 * @date 2020-10-29 029 15:22
 */
public interface AuthenticationClientService {

    /**
     * 验证
     *
     * @param dto dto
     * @return UserLoginDto
     */
    UserLoginDto verification(VerificationDto dto);

    /**
     * 访问
     *
     * @param dto dto
     */
    void access(AccessDto dto);
}
