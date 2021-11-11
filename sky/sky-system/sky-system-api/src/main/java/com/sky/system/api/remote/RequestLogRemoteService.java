package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.RequestLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RequestLogRemoteService {

    @PostMapping(value = "/request-log")
    ResponseDto<Boolean> save(@RequestBody RequestLog requestLog);
}
