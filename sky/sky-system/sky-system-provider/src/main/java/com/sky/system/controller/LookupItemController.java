package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.LookupItemDto;
import com.sky.system.api.dto.LookupItemQueryDto;
import com.sky.system.api.model.LookupItem;
import com.sky.system.api.remote.LookupItemRemoteService;
import com.sky.system.service.LookupItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "LookupItem信息")
@ApiSort(value = 700)
@RestController
public class LookupItemController implements LookupItemRemoteService {

    @Autowired
    private LookupItemService lookupItemService;

    @ApiOperation(value = "根据ID查询", position = 1)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemDto")
    @Override
    public ResponseDto<LookupItem> get(LookupItemDto dto) {
        return new ResponseDto<>(this.lookupItemService.getById(dto.getId()));
    }

    @ApiOperation(value = "保存", position = 2)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemDto")
    @Override
    public ResponseDto<Boolean> save(LookupItemDto dto) {
        return new ResponseDto<>(this.lookupItemService.create(dto));
    }

    @ApiOperation(value = "修改", position = 3)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemDto")
    @Override
    public ResponseDto<Boolean> update(LookupItemDto dto) {
        return new ResponseDto<>(this.lookupItemService.update(dto));
    }

    @ApiOperation(value = "删除", position = 4)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemDto")
    @Override
    public ResponseDto<Boolean> delete(LookupItemDto dto) {
        return new ResponseDto<>(this.lookupItemService.removeById(dto.getId()));
    }

    @ApiOperation(value = "分页列表", position = 5)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemQueryDto")
    @Override
    public ResponseDto<IPage<LookupItem>> page(LookupItemQueryDto dto) {
        return new ResponseDto<>(this.lookupItemService.page(dto));
    }

    @ApiOperation(value = "列表", position = 6)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "LookupItemDto")
    @Override
    public ResponseDto<List<LookupItem>> list(LookupItemDto dto) {
        return new ResponseDto<>(this.lookupItemService.list(dto));
    }
}
