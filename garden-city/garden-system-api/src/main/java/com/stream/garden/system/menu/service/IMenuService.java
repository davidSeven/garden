package com.stream.garden.system.menu.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.vo.MenuVO;

import java.util.List;

/**
 * @author garden
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 获取用户菜单
     *
     * @param userId 用户id
     * @return 菜单
     * @throws ApplicationException e
     */
    List<MenuVO> getUserMenu(String userId) throws ApplicationException;

    /**
     * 获取角色菜单
     *
     * @param roleId 角色id
     * @return 菜单
     * @throws ApplicationException e
     */
    List<MenuVO> getRoleMenu(String roleId) throws ApplicationException;

}
