package com.forest.system.service;

import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @date 2020-10-19 019 14:49
 */
public interface TokenRemoteService {

    /**
     * 验证
     *
     * @param token token
     * @return UserDto
     */
    @PostMapping(value = "/system/login/valid")
    ResponseDto<UserDto> valid(@RequestBody String token);
}
