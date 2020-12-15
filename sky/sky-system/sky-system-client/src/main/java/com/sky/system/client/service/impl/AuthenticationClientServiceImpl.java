package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;
import com.sky.system.client.feign.AuthenticationFeign;
import com.sky.system.client.service.AuthenticationClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2020-12-15 015 13:40
 */
@Service
public class AuthenticationClientServiceImpl implements AuthenticationClientService {

    @Autowired
    private AuthenticationFeign authenticationFeign;

    @Override
    public UserLoginDto verification(VerificationDto dto) {
        return ResponseDto.getData(this.authenticationFeign.verification(dto));
    }

    @Override
    public void access(AccessDto dto) {
        this.authenticationFeign.access(dto);
    }
}
