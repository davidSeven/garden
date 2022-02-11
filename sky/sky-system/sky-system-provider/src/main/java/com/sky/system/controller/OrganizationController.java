package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.OrganizationDto;
import com.sky.system.api.remote.OrganizationRemoteService;
import com.sky.system.api.vo.OrganizationVO;
import com.sky.system.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "组织信息")
@RestController
public class OrganizationController implements OrganizationRemoteService {

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "dto", value = "组织信息", required = true, dataType = "OrganizationDto")
    @Override
    public ResponseDto<Boolean> save(OrganizationDto dto) {
        return new ResponseDto<>(this.organizationService.save(dto)).ok();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "dto", value = "组织信息", required = true, dataType = "OrganizationDto")
    @Override
    public ResponseDto<Boolean> update(OrganizationDto dto) {
        return new ResponseDto<>(this.organizationService.update(dto)).ok();
    }

    @ApiOperation(value = "树形列表")
    @ApiImplicitParam(name = "dto", value = "组织信息", required = true, dataType = "OrganizationDto")
    @Override
    public ResponseDto<List<OrganizationVO>> tree(OrganizationDto dto) {
        return new ResponseDto<>(this.organizationService.tree(dto)).ok();
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "dto", value = "组织信息", required = true, dataType = "OrganizationDto")
    @Override
    public ResponseDto<Integer> delete(OrganizationDto dto) {
        return new ResponseDto<>(this.organizationService.physicalDeleteById(dto.getId())).ok();
    }
}
