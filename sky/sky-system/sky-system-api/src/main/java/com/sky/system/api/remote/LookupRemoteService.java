package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.LookupDto;
import com.sky.system.api.dto.LookupQueryDto;
import com.sky.system.api.model.Lookup;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LookupRemoteService {

    @PostMapping(value = "/lookup/get")
    ResponseDto<Lookup> get(@RequestBody LookupDto dto);

    @PostMapping(value = "/lookup")
    ResponseDto<Boolean> save(@RequestBody LookupDto dto);

    @PutMapping(value = "/lookup")
    ResponseDto<Boolean> update(@RequestBody LookupDto dto);

    @DeleteMapping(value = "/lookup")
    ResponseDto<Boolean> delete(@RequestBody LookupDto dto);

    @PostMapping(value = "/lookup/page")
    ResponseDto<IPage<Lookup>> page(@RequestBody LookupQueryDto dto);

    @PostMapping(value = "/lookup/list")
    ResponseDto<List<Lookup>> list(@RequestBody LookupDto dto);

}
