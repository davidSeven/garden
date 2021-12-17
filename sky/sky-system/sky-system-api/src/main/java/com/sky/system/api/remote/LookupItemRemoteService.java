package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.LookupItemDto;
import com.sky.system.api.dto.LookupItemQueryDto;
import com.sky.system.api.model.LookupItem;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LookupItemRemoteService {

    @PostMapping(value = "/lookup-item/get")
    ResponseDto<LookupItem> get(@RequestBody LookupItemDto dto);

    @PostMapping(value = "/lookup-item")
    ResponseDto<Boolean> save(@RequestBody LookupItemDto dto);

    @PutMapping(value = "/lookup-item")
    ResponseDto<Boolean> update(@RequestBody LookupItemDto dto);

    @DeleteMapping(value = "/lookup-item")
    ResponseDto<Boolean> delete(@RequestBody LookupItemDto dto);

    @PostMapping(value = "/lookup-item/page")
    ResponseDto<IPage<LookupItem>> page(@RequestBody LookupItemQueryDto dto);

    @PostMapping(value = "/lookup-item/list")
    ResponseDto<List<LookupItem>> list(@RequestBody LookupItemDto dto);

}
