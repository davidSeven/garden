package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @date 2020-12-15 015 13:37
 */
public interface AuthenticationRemoteService {

    @PostMapping(value = "/authentication/verification")
    ResponseDto<UserLoginDto> verification(@RequestBody VerificationDto dto);

    @PostMapping(value = "/authentication/access")
    ResponseDto<Void> access(@RequestBody AccessDto dto);
}
