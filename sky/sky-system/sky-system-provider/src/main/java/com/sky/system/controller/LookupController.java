package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.LookupDto;
import com.sky.system.api.dto.LookupQueryDto;
import com.sky.system.api.model.Lookup;
import com.sky.system.api.remote.LookupRemoteService;
import com.sky.system.service.LookupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Lookup信息")
@ApiSort(value = 600)
@RestController
public class LookupController implements LookupRemoteService {

    @Autowired
    private LookupService lookupService;

    @ApiOperation(value = "根据ID查询", position = 1)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupDto")
    @Override
    public ResponseDto<Lookup> get(LookupDto dto) {
        return new ResponseDto<>(this.lookupService.getById(dto.getId()));
    }

    @ApiOperation(value = "保存", position = 2)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupDto")
    @Override
    public ResponseDto<Boolean> save(LookupDto dto) {
        return new ResponseDto<>(this.lookupService.create(dto));
    }

    @ApiOperation(value = "修改", position = 3)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupDto")
    @Override
    public ResponseDto<Boolean> update(LookupDto dto) {
        return new ResponseDto<>(this.lookupService.update(dto));
    }

    @ApiOperation(value = "删除", position = 4)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupDto")
    @Override
    public ResponseDto<Boolean> delete(LookupDto dto) {
        return new ResponseDto<>(this.lookupService.removeById(dto.getId()));
    }

    @ApiOperation(value = "分页列表", position = 5)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupQueryDto")
    @Override
    public ResponseDto<IPage<Lookup>> page(LookupQueryDto dto) {
        return new ResponseDto<>(this.lookupService.page(dto));
    }

    @ApiOperation(value = "列表", position = 6)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupDto")
    @Override
    public ResponseDto<List<Lookup>> list(LookupDto dto) {
        return new ResponseDto<>(this.lookupService.list(dto));
    }
}
