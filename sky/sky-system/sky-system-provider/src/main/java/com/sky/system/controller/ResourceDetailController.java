package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.ResourceDetailDto;
import com.sky.system.api.model.ResourceDetail;
import com.sky.system.service.ResourceDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "资源清单管理")
@RestController
@RequestMapping(value = "/resource/detail")
public class ResourceDetailController {

    @Autowired
    private ResourceDetailService resourceDetailService;

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "dto", value = "参数", dataType = "ResourceDetailDto")
    @PostMapping("/page")
    public ResponseDto<IPage<ResourceDetail>> page(@RequestBody ResourceDetailDto dto) {
        IPage<ResourceDetail> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<ResourceDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ResourceDetail::getMenuId, dto.getMenuId());
        queryWrapper.eq(ResourceDetail::getResourceApplicationId, dto.getResourceApplicationId());
        if (StringUtils.isNotEmpty(dto.getMethod())) {
            queryWrapper.eq(ResourceDetail::getMethod, dto.getMethod().trim());
        }
        if (StringUtils.isNotEmpty(dto.getUrl())) {
            queryWrapper.like(ResourceDetail::getUrl, dto.getUrl().trim());
        }
        if (StringUtils.isNotEmpty(dto.getName())) {
            queryWrapper.like(ResourceDetail::getName, dto.getName().trim());
        }
        // 升序
        queryWrapper.orderByAsc(ResourceDetail::getSort);
        return new ResponseDto<>(this.resourceDetailService.page(page, queryWrapper)).ok();
    }
}
