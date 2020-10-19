package com.forest.system.service.client;

import com.forest.system.dto.UserDto;

/**
 * @date 2020-10-19 019 15:06
 */
public interface TokenClientService {

    /**
     * 验证
     *
     * @param token token
     * @return UserDto
     */
    UserDto valid(String token);
}
