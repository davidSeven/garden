package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;
import com.sky.system.api.remote.I18nRemoteService;
import com.sky.system.service.I18nService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "国际化信息")
@ApiSort(value = 500)
@RestController
public class I18nController implements I18nRemoteService {

    @Autowired
    private I18nService i18nService;

    @ApiOperation(value = "根据ID查询", position = 1)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<I18n> get(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.getById(dto.getId()));
    }

    @ApiOperation(value = "保存", position = 2)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> save(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.create(dto));
    }

    @ApiOperation(value = "修改", position = 3)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> update(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.update(dto));
    }

    @ApiOperation(value = "删除", position = 4)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> delete(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.removeById(dto.getId()));
    }

    @ApiOperation(value = "分页列表", position = 5)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nQueryDto")
    @Override
    public ResponseDto<IPage<I18n>> page(I18nQueryDto dto) {
        return new ResponseDto<>(this.i18nService.page(dto));
    }

    @ApiOperation(value = "列表", position = 6)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<List<I18n>> list(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.list(dto));
    }
}
