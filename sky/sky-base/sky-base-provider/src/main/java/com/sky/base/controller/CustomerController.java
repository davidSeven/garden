package com.sky.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;
import com.sky.base.api.remote.CustomerRemoteService;
import com.sky.base.service.CustomerService;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.validator.groups.Delete;
import com.sky.framework.validator.groups.Save;
import com.sky.framework.validator.groups.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "客户")
@ApiSort(100)
@RestController
public class CustomerController implements CustomerRemoteService {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "根据ID查询", position = 1)
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @Override
    public ResponseDto<Customer> get(Long id) {
        return new ResponseDto<>(customerService.getById(id));
    }

    @ApiOperation(value = "根据编码查询", position = 2)
    @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String")
    @Override
    public ResponseDto<Customer> getByCode(String code) {
        return new ResponseDto<>(customerService.getByCode(code));
    }

    @ApiOperation(value = "创建", position = 3)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "CustomerDto")
    @Override
    public ResponseDto<Boolean> create(@RequestBody @Validated(value = {Save.class}) CustomerDto dto) {
        return new ResponseDto<>(customerService.create(dto));
    }

    @ApiOperation(value = "修改", position = 4)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "CustomerDto")
    @Override
    public ResponseDto<Boolean> update(@RequestBody @Validated(value = {Update.class}) CustomerDto dto) {
        return new ResponseDto<>(customerService.update(dto));
    }

    @ApiOperation(value = "删除", position = 5)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "CustomerDto")
    @Override
    public ResponseDto<Boolean> delete(@RequestBody @Validated(value = {Delete.class}) CustomerDto dto) {
        return new ResponseDto<>(customerService.removeById(dto.getId()));
    }

    @ApiOperation(value = "分页列表", position = 6)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "CustomerQueryDto")
    @Override
    public ResponseDto<IPage<Customer>> page(CustomerQueryDto dto) {
        return new ResponseDto<>(customerService.page(dto));
    }

    @ApiOperation(value = "列表", position = 7)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "CustomerDto")
    @Override
    public ResponseDto<List<Customer>> list(CustomerDto dto) {
        return new ResponseDto<>(customerService.list(dto));
    }
}
