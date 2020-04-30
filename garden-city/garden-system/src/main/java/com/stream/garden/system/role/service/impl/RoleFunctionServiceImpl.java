package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.function.model.Function;
import com.stream.garden.system.function.service.IFunctionFieldService;
import com.stream.garden.system.function.service.IFunctionService;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.role.dao.IRoleFunctionDao;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.model.RoleMenu;
import com.stream.garden.system.role.service.IRoleFunctionFieldService;
import com.stream.garden.system.role.service.IRoleFunctionService;
import com.stream.garden.system.role.service.IRoleMenuService;
import com.stream.garden.system.role.vo.MenuFunctionVO;
import com.stream.garden.system.role.vo.RoleMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author garden
 * @date 2019/7/22 22:29
 */
@Service
public class RoleFunctionServiceImpl extends AbstractBaseService<RoleFunction, IRoleFunctionDao> implements IRoleFunctionService {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IFunctionService functionService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IRoleFunctionFieldService roleFunctionFieldService;
    @Autowired
    private IFunctionFieldService functionFieldService;

    @Override
    public int deleteByRoleId(String roleId) throws ApplicationException {
        return this.getMapper().deleteByRoleId(new RoleFunction(roleId));
    }

    @Override
    public void saveMenuFunction(RoleMenuVO vo) throws ApplicationException {
        String roleId = vo.getRoleId();
        this.roleMenuService.deleteByRoleId(roleId);
        this.deleteByRoleId(roleId);
        List<MenuFunctionVO> voList = vo.getVoList();
        if (CollectionUtil.isNotEmpty(voList)) {
            // 角色菜单集合
            List<RoleMenu> roleMenuList = new ArrayList<>();
            // 角色功能集合
            List<RoleFunction> roleFunctionList = new ArrayList<>();
            for (MenuFunctionVO item : voList) {
                if (MenuFunctionVO.TYPE_MENU == item.getType()) {
                    roleMenuList.add(new RoleMenu(roleId, item.getId()));
                } else if (MenuFunctionVO.TYPE_FUNCTION == item.getType()) {
                    roleFunctionList.add(new RoleFunction(roleId, item.getId(), item.getType()));
                }
            }
            // 保存数据
            if (CollectionUtil.isNotEmpty(roleMenuList)) {
                this.roleMenuService.insertBatch(roleMenuList);
            }
            if (CollectionUtil.isNotEmpty(roleFunctionList)) {
                this.insertBatch(roleFunctionList);
            }
            // 保存字段数据
            this.roleFunctionFieldService.saveBatch(roleId, vo.getFieldList());
        }
    }

    @Override
    public List<MenuFunctionVO> getMenuFunction(String roleId) throws ApplicationException {
        // 查询启用的菜单
        Menu paramsMenu = new Menu();
        paramsMenu.setState(SystemConstant.STATE_1);
        List<Menu> menuList = menuService.list(paramsMenu);

        // 查询配置的功能信息
        Function paramsFunction = new Function();
        List<Function> functionList = functionService.list(paramsFunction);

        // 查询字段信息


        // 查询当前角色已经设置的菜单信息
        List<RoleMenu> roleMenuList = this.roleMenuService.list(new RoleMenu(roleId));
        Set<String> roleMenuSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(roleMenuList)) {
            roleMenuSet = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        }
        // 查询当前角色已经设置的功能信息
        List<RoleFunction> roleFunctionList = this.list(new RoleFunction(roleId));
        Set<String> roleFunctionSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(roleFunctionList)) {
            roleFunctionSet = roleFunctionList.stream().map(RoleFunction::getFunctionId).collect(Collectors.toSet());
        }
        return getMenuFunction(menuList, functionList, roleMenuSet, roleFunctionSet);
    }

    private List<MenuFunctionVO> getMenuFunction(List<Menu> menuList, List<Function> functionList, Set<String> roleMenuSet, Set<String> roleFunctionSet) {
        List<MenuFunctionVO> menuFunctionVOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                if (roleMenuSet.contains(menu.getId())) {
                    menuFunctionVOList.add(new MenuFunctionVO(menu.getId(), menu.getName(), menu.getParentId(), true));
                } else {
                    menuFunctionVOList.add(new MenuFunctionVO(menu.getId(), menu.getName(), menu.getParentId()));
                }
            }
        }
        if (CollectionUtil.isNotEmpty(functionList)) {
            for (Function function : functionList) {
                if (roleFunctionSet.contains(function.getId())) {
                    menuFunctionVOList.add(new MenuFunctionVO(MenuFunctionVO.TYPE_FUNCTION, function.getId(), function.getName(), function.getMenuId(), true));
                } else {
                    menuFunctionVOList.add(new MenuFunctionVO(MenuFunctionVO.TYPE_FUNCTION, function.getId(), function.getName(), function.getMenuId()));
                }
            }
        }
        return menuFunctionVOList;
    }
}
