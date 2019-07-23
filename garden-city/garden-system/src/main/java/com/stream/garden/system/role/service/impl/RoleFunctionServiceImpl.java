package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.function.model.Function;
import com.stream.garden.system.function.service.IFunctionService;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.role.dao.IRoleFunctionDao;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.service.IRoleFunctionService;
import com.stream.garden.system.role.vo.MenuFunctionVO;
import com.stream.garden.system.role.vo.RoleFunctionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019/7/22 22:29
 */
@Service
public class RoleFunctionServiceImpl extends AbstractBaseService<RoleFunction, String> implements IRoleFunctionService {

    public RoleFunctionServiceImpl(IRoleFunctionDao iRoleFunctionDao) {
        super(iRoleFunctionDao);
    }

    public IRoleFunctionDao getMapper() {
        return (IRoleFunctionDao) super.baseMapper;
    }

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IFunctionService functionService;

    @Override
    public int saveRoleFunction(RoleFunctionVO vo) throws ApplicationException {
        // 先删后增
        this.deleteByRoleId(vo.getRoleId());
        if (CollectionUtil.isNotEmpty(vo.getRoleFunctionList())) {
            for (RoleFunction roleFunction : vo.getRoleFunctionList()) {
                roleFunction.setRoleId(vo.getRoleId());
            }
            return this.insertBatch(vo.getRoleFunctionList());
        }
        return 0;
    }

    @Override
    public int deleteByRoleId(String roleId) throws ApplicationException {
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setRoleId(roleId);
        return this.getMapper().deleteByRoleId(roleFunction);
    }

    @Override
    public List<MenuFunctionVO> getMenuFunction() throws ApplicationException {
        Menu paramsMenu = new Menu();
        paramsMenu.setState("1");
        List<Menu> menuList = menuService.list(paramsMenu);

        Function paramsFunction = new Function();
        List<Function> functionList = functionService.list(paramsFunction);

        List<MenuFunctionVO> menuFunctionVOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                menuFunctionVOList.add(new MenuFunctionVO(menu.getId(), menu.getName(), menu.getParentId()));
            }
        }
        if (CollectionUtil.isNotEmpty(functionList)) {
            for (Function function : functionList) {
                menuFunctionVOList.add(new MenuFunctionVO(MenuFunctionVO.TYPE_FUNCTION, function.getId(), function.getName(), function.getMenuId()));
            }
        }

        return menuFunctionVOList;
    }
}
