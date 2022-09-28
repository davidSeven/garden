package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.ResourceApplicationDto;
import com.sky.system.api.model.ResourceApplication;
import com.sky.system.service.ResourceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "资源应用管理")
@RestController
@RequestMapping(value = "/resource/application")
public class ResourceApplicationController {

    @Autowired
    private ResourceApplicationService resourceApplicationService;

    @ApiOperation(value = "查询")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @GetMapping
    public ResponseDto<ResourceApplication> get(ResourceApplication resourceApplication) {
        return ResponseDto.getSuccessResponseDto(this.resourceApplicationService.getById(resourceApplication.getId()));
    }

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @PostMapping
    public ResponseDto<Boolean> save(@RequestBody ResourceApplication resourceApplication) {
        return new ResponseDto<Boolean>().ok().setData(this.resourceApplicationService.save(resourceApplication));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @PutMapping
    public ResponseDto<Boolean> update(@RequestBody ResourceApplication resourceApplication) {
        return new ResponseDto<Boolean>().ok().setData(this.resourceApplicationService.updateById(resourceApplication));
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @DeleteMapping
    public ResponseDto<Boolean> remove(@RequestBody ResourceApplication resourceApplication) {
        return new ResponseDto<Boolean>().ok().setData(this.resourceApplicationService.removeById(resourceApplication.getId()));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "dto", value = "参数", dataType = "ResourceApplicationDto")
    @PostMapping("/page")
    public ResponseDto<IPage<ResourceApplication>> page(@RequestBody ResourceApplicationDto dto) {
        IPage<ResourceApplication> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<ResourceApplication> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(dto.getName())) {
            queryWrapper.like(ResourceApplication::getName, dto.getName().trim());
        }
        // 升序
        queryWrapper.orderByAsc(ResourceApplication::getSort);
        return new ResponseDto<>(this.resourceApplicationService.page(page, queryWrapper)).ok();
    }

    @ApiOperation(value = "应用列表")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @PostMapping(value = "/applicationList")
    public ResponseDto<List<String>> applicationList(@RequestBody ResourceApplication resourceApplication) {
        return new ResponseDto<>(this.resourceApplicationService.applicationList()).ok();
    }

    @ApiOperation(value = "刷新资源清单")
    @ApiImplicitParam(name = "resourceApplication", value = "参数", dataType = "ResourceApplication")
    @PostMapping(value = "/refreshResourceDetail")
    public ResponseDto<Boolean> refreshResourceDetail(@RequestBody ResourceApplication resourceApplication) {
        return new ResponseDto<>(this.resourceApplicationService.refreshResourceDetail(resourceApplication)).ok();
    }

}
