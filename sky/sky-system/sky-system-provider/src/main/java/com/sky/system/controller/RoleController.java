package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.RoleAuthDto;
import com.sky.system.api.dto.RoleQueryDto;
import com.sky.system.api.model.Role;
import com.sky.system.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询")
    @ApiImplicitParam(name = "role", value = "参数", dataType = "Role")
    @GetMapping
    public ResponseDto<Role> get(Role role) {
        return ResponseDto.getSuccessResponseDto(this.roleService.getById(role.getId()));
    }

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "role", value = "参数", dataType = "Role")
    @PostMapping
    public ResponseDto<Boolean> save(@RequestBody Role role) {
        return new ResponseDto<Boolean>().ok().setData(this.roleService.save(role));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "role", value = "参数", dataType = "Role")
    @PutMapping
    public ResponseDto<Boolean> update(@RequestBody Role role) {
        return new ResponseDto<Boolean>().ok().setData(this.roleService.updateById(role));
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "role", value = "参数", dataType = "Role")
    @DeleteMapping
    public ResponseDto<Boolean> remove(@RequestBody Role role) {
        return new ResponseDto<Boolean>().ok().setData(this.roleService.removeById(role.getId()));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "dto", value = "参数", dataType = "RoleQueryDto")
    @PostMapping("/page")
    public ResponseDto<IPage<Role>> page(@RequestBody RoleQueryDto dto) {
        IPage<Role> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(dto.getName())) {
            queryWrapper.like(Role::getName, dto.getName().trim());
        }
        // 升序
        queryWrapper.orderByAsc(Role::getSort);
        return new ResponseDto<IPage<Role>>().ok().setData(this.roleService.page(page, queryWrapper));
    }

    @ApiOperation(value = "角色权限列表")
    @ApiImplicitParam(name = "role", value = "参数", dataType = "Role")
    @PostMapping(value = "/authList")
    public ResponseDto<List<Long>> authList(@RequestBody Role role) {
        return new ResponseDto<List<Long>>().ok().setData(this.roleService.authList(role.getId()));
    }

    @ApiOperation(value = "设置角色权限")
    @ApiImplicitParam(name = "dto", value = "参数", dataType = "RoleAuthDto")
    @PutMapping(value = "/auth")
    public ResponseDto<Boolean> auth(@RequestBody RoleAuthDto dto) {
        return new ResponseDto<Boolean>().ok().setData(this.roleService.auth(dto));
    }
}
