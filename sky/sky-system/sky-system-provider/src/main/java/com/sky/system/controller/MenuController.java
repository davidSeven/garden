package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.MenuQueryDto;
import com.sky.system.api.model.Menu;
import com.sky.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2020-12-16 016 11:56
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "查询")
    @ApiImplicitParam(name = "menu", value = "信息", dataType = "Menu")
    @GetMapping
    public ResponseDto<Menu> get(Menu menu) {
        return ResponseDto.getSuccessResponseDto(this.menuService.getById(menu.getId()));
    }

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "menu", value = "信息", dataType = "Menu")
    @PostMapping
    public ResponseDto<Boolean> save(@RequestBody Menu menu) {
        return new ResponseDto<Boolean>().ok().setData(this.menuService.save(menu));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "menu", value = "信息", dataType = "Menu")
    @PutMapping
    public ResponseDto<Boolean> update(@RequestBody Menu menu) {
        return new ResponseDto<Boolean>().ok().setData(this.menuService.updateById(menu));
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "menu", value = "信息", dataType = "Menu")
    @DeleteMapping
    public ResponseDto<Boolean> remove(@RequestBody Menu menu) {
        return new ResponseDto<Boolean>().ok().setData(this.menuService.removeById(menu.getId()));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "queryDto", value = "查询信息", dataType = "MenuQueryDto")
    @PostMapping("/page")
    public ResponseDto<IPage<Menu>> page(@RequestBody MenuQueryDto queryDto) {
        IPage<Menu> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(queryDto.getName())) {
            queryWrapper.like(Menu::getName, queryDto.getName().trim());
        }
        return new ResponseDto<IPage<Menu>>().ok().setData(this.menuService.page(page, queryWrapper));
    }
}
