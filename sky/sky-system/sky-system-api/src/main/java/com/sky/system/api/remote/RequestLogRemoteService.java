package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.RequestLogQueryDto;
import com.sky.system.api.model.RequestLog;
import org.springframework.web.bind.annotation.*;

public interface RequestLogRemoteService {

    @GetMapping(value = "/request-log")
    ResponseDto<RequestLog> get(@RequestParam(value = "id") Long id);

    @PostMapping(value = "/request-log")
    ResponseDto<Boolean> save(@RequestBody RequestLog requestLog);

    @PutMapping(value = "/request-log")
    ResponseDto<Boolean> update(@RequestBody RequestLog requestLog);

    @PostMapping(value = "/request-log/page")
    ResponseDto<IPage<RequestLog>> page(@RequestBody RequestLogQueryDto dto);

}
