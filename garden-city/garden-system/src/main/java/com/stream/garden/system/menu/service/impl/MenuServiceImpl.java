package com.stream.garden.system.menu.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.menu.dao.IMenuDao;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.menu.vo.MenuVO;
import com.stream.garden.system.user.service.IUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 */
@Service
public class MenuServiceImpl extends AbstractBaseService<Menu, IMenuDao> implements IMenuService {

    @Autowired
    private IUserService userService;

    @Override
    public int insert(Menu menu) throws ApplicationException {
        try {
            // 设置父级id
            if (StringUtils.isEmpty(menu.getParentId())) {
                menu.setParentId("0");
            }
            // 同一个菜单下两个子菜单的名字不能一样
            Menu paramMenu = new Menu();
            paramMenu.setName(menu.getName());
            paramMenu.setParentId(menu.getParentId());
            if (super.exists(paramMenu)) {
                throw new ApplicationException(SystemExceptionCode.MENU_NAME_REPEAT, menu.getName());
            }
            return super.insertSelective(menu);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e);
        }
    }

    @Override
    public int update(Menu menu) throws ApplicationException {
        // 同一个菜单下两个子菜单的名字不能一样
        Menu paramMenu = new Menu();
        // sql中的条件是<>
        paramMenu.setId(menu.getId());
        paramMenu.setName(menu.getName());
        // 设置父级id
        if (StringUtils.isEmpty(menu.getParentId())) {
            menu.setParentId("0");
        }
        paramMenu.setParentId(menu.getParentId());
        // 根据名字和父级id查询，如果返回的条数大于1，则存在相同的记录
        if (super.exists(paramMenu)) {
            throw new ApplicationException(SystemExceptionCode.MENU_NAME_REPEAT, menu.getName());
        }
        return super.updateSelective(menu);
    }

    @Override
    public int delete(Serializable... strings) throws ApplicationException {
        if (ArrayUtils.isEmpty(strings)) {
            return 0;
        }
        for (Serializable id : strings) {
            Menu paramMenu = new Menu();
            paramMenu.setParentId((String) id);
            // 根据parentId查询记录，如果存在，则存在子级，则不能删除
            if (super.exists(paramMenu)) {
                throw new ApplicationException(SystemExceptionCode.MENU_EXISTS_CHILDREN_DELETE_EXCEPTION);
            }
        }
        return super.delete(strings);
    }

    @Override
    public List<MenuVO> getUserMenu(String userId) throws ApplicationException {

        return new ArrayList<>();
    }

    @Override
    public List<MenuVO> getRoleMenu(String roleId) throws ApplicationException {
        List<Menu> menuList = this.getMapper().getRoleMenu(roleId);
        if (CollectionUtil.isNotEmpty(menuList)) {
            return toMenuTree(menuList);
        }
        return new ArrayList<>();
    }

    private List<MenuVO> toMenuTree(List<Menu> menuList) {
        List<MenuVO> voList = new ArrayList<>();
        for (Menu menu : menuList) {
            if ("0".equals(menu.getParentId())) {
                MenuVO vo = new MenuVO(menu);
                vo.setChildren(getChild(menu.getId(), menuList));
                voList.add(vo);
            }
        }
        return voList;
    }

    private List<MenuVO> getChild(String id, List<Menu> menuList) {
        List<MenuVO> voList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (id.equals(menu.getParentId())) {
                MenuVO vo = new MenuVO(menu);
                voList.add(vo);
            }
        }
        return voList;
    }
}
