package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.dto.RoleAuthDto;
import com.sky.system.api.model.Role;
import com.sky.system.api.model.RoleMenu;
import com.sky.system.dao.RoleDao;
import com.sky.system.service.RoleMenuService;
import com.sky.system.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Long> authList(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(RoleMenu::getMenuId);
        queryWrapper.eq(RoleMenu::getRoleId, id);
        List<RoleMenu> roleMenuList = roleMenuService.list(queryWrapper);
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return Collections.emptyList();
        }
        return roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public boolean auth(RoleAuthDto dto) {
        Long id = dto.getId();
        roleMenuService.deleteByRoleId(id);
        List<Long> menuIdList = dto.getMenuIdList();
        if (CollectionUtils.isNotEmpty(menuIdList)) {
            List<RoleMenu> roleMenuList = new ArrayList<>();
            for (Long menuId : menuIdList) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(id);
                roleMenu.setMenuId(menuId);
                roleMenuList.add(roleMenu);
            }
            roleMenuService.saveBatch(roleMenuList);
        }
        return true;
    }
}
