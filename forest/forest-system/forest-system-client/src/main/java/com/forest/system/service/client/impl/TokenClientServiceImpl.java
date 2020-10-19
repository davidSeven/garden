package com.forest.system.service.client.impl;

import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import com.forest.system.service.client.TokenClientService;
import com.forest.system.service.feign.TokenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2020-10-19 019 15:06
 */
@Service
public class TokenClientServiceImpl implements TokenClientService {

    @Autowired
    private TokenFeignService tokenFeignService;

    @Override
    public UserDto valid(String token) {
        return ResponseDto.getData(this.tokenFeignService.valid(token));
    }
}
