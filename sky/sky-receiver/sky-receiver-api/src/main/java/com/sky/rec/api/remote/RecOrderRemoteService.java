package com.sky.rec.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.rec.api.dto.RecOrderDto;
import com.sky.rec.api.model.RecOrder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RecOrderRemoteService {

    @PostMapping(value = "/receiver")
    ResponseDto<Boolean> create(@RequestBody @Validated RecOrderDto dto);

    @PutMapping(value = "/receiver")
    ResponseDto<Boolean> update(@RequestBody @Validated RecOrderDto dto);

    @DeleteMapping(value = "/receiver")
    ResponseDto<Boolean> delete(@RequestBody @Validated RecOrderDto dto);

    @PostMapping(value = "/receiver/list")
    ResponseDto<IPage<RecOrder>> list(@RequestBody RecOrder order);

    @PostMapping(value = "/audit")
    ResponseDto<Boolean> audit(@RequestBody @Validated RecOrderDto dto);
}
