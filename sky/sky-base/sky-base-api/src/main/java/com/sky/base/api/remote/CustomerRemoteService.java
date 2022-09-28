package com.sky.base.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerRemoteService {

    @GetMapping(value = "/customer/{id}")
    ResponseDto<Customer> get(@PathVariable(value = "id") Long id);

    @GetMapping(value = "/customer/code/{code}")
    ResponseDto<Customer> getByCode(@PathVariable(value = "code") String code);

    @PostMapping(value = "/customer")
    ResponseDto<Boolean> create(@RequestBody CustomerDto dto);

    @PutMapping(value = "/customer")
    ResponseDto<Boolean> update(@RequestBody @Validated CustomerDto dto);

    @DeleteMapping(value = "/customer")
    ResponseDto<Boolean> delete(@RequestBody @Validated CustomerDto dto);

    @PostMapping(value = "/customer/page")
    ResponseDto<IPage<Customer>> page(@RequestBody CustomerQueryDto dto);

    @PostMapping(value = "/customer/list")
    ResponseDto<List<Customer>> list(@RequestBody CustomerDto dto);
}
