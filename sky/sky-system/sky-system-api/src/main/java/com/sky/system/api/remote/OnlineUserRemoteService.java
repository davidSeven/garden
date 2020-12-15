package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnlineUserRemoteService {

    @PostMapping(value = "/online-user/visit")
    ResponseDto<Boolean> visit(@RequestBody String token);
}
