package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.MenuQueryDto;
import com.sky.system.api.model.Menu;
import com.sky.system.api.vo.MenuVO;
import com.sky.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @date 2020-12-16 016 11:56
 */
@Api(tags = "菜单管理")
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
        if (Objects.isNull(menu.getParentId())) {
            menu.setParentId(0L);
        }
        return new ResponseDto<Boolean>().ok().setData(this.menuService.save(menu));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "menu", value = "信息", dataType = "Menu")
    @PutMapping
    public ResponseDto<Boolean> update(@RequestBody Menu menu) {
        if (Objects.isNull(menu.getParentId())) {
            menu.setParentId(0L);
        }
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
        // 只查询父级的
        queryWrapper.eq(Menu::getParentId, 0);
        // 升序
        queryWrapper.orderByAsc(Menu::getSort);
        return new ResponseDto<IPage<Menu>>().ok().setData(this.menuService.page(page, queryWrapper));
    }

    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "menu", value = "查询信息", dataType = "Menu")
    @PostMapping("/list")
    public ResponseDto<List<Menu>> list(@RequestBody Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(menu.getParentId())) {
            queryWrapper.eq(Menu::getParentId, menu.getParentId());
        }
        if (StringUtils.isNotEmpty(menu.getName())) {
            queryWrapper.like(Menu::getName, menu.getName().trim());
        }
        // 升序
        queryWrapper.orderByAsc(Menu::getSort);
        return new ResponseDto<List<Menu>>().ok().setData(this.menuService.list(queryWrapper));
    }

    @ApiOperation(value = "菜单列表")
    @PostMapping("/treeList")
    public ResponseDto<List<MenuVO>> treeList() {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        // 升序
        queryWrapper.orderByAsc(Menu::getSort);
        List<Menu> list = this.menuService.list(queryWrapper);
        return new ResponseDto<List<MenuVO>>().ok().setData(listToTree(list));
    }

    private List<MenuVO> listToTree(List<Menu> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Long parentId = 0L;
        return getChildren(parentId, list);
    }

    private List<MenuVO> getChildren(Long id, List<Menu> list) {
        List<MenuVO> children = new ArrayList<>();
        for (Menu menu : list) {
            if (id.equals(menu.getParentId())) {
                MenuVO vo = BeanHelpUtil.convertDto(menu, MenuVO.class);
                vo.builderMeta(menu.getName(), menu.getIcon());
                vo.setChildren(getChildren(menu.getId(), list));
                children.add(vo);
            }
        }
        return children;
    }
}
