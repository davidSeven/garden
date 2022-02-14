package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.I18nMissQueryDto;
import com.sky.system.api.model.I18nMiss;
import com.sky.system.service.I18nMissService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "国际化缺失信息")
@ApiSort(value = 510)
@RestController(value = "/i18n-miss")
public class I18nMissController {

    @Autowired
    private I18nMissService i18nMissService;

    @ApiOperation(value = "查询")
    @ApiImplicitParam(name = "i18nMiss", value = "参数", dataType = "I18nMiss")
    @GetMapping
    public ResponseDto<I18nMiss> get(I18nMiss i18nMiss) {
        return ResponseDto.getSuccessResponseDto(this.i18nMissService.getById(i18nMiss.getId()));
    }

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "i18nMiss", value = "参数", dataType = "I18nMiss")
    @PostMapping
    public ResponseDto<Boolean> save(@RequestBody I18nMiss i18nMiss) {
        return new ResponseDto<Boolean>().ok().setData(this.i18nMissService.save(i18nMiss));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "i18nMiss", value = "参数", dataType = "I18nMiss")
    @PutMapping
    public ResponseDto<Boolean> update(@RequestBody I18nMiss i18nMiss) {
        return new ResponseDto<Boolean>().ok().setData(this.i18nMissService.updateById(i18nMiss));
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "i18nMiss", value = "参数", dataType = "I18nMiss")
    @DeleteMapping
    public ResponseDto<Boolean> remove(@RequestBody I18nMiss i18nMiss) {
        return new ResponseDto<Boolean>().ok().setData(this.i18nMissService.removeById(i18nMiss.getId()));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "dto", value = "参数", dataType = "I18nMissQueryDto")
    @PostMapping("/page")
    public ResponseDto<IPage<I18nMiss>> page(@RequestBody I18nMissQueryDto dto) {
        IPage<I18nMiss> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<I18nMiss> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(dto.getCode())) {
            queryWrapper.like(I18nMiss::getCode, dto.getCode().trim());
        }
        if (StringUtils.isNotEmpty(dto.getLanguageType())) {
            queryWrapper.like(I18nMiss::getLanguageType, dto.getLanguageType().trim());
        }
        return new ResponseDto<IPage<I18nMiss>>().ok().setData(this.i18nMissService.page(page, queryWrapper));
    }

}
