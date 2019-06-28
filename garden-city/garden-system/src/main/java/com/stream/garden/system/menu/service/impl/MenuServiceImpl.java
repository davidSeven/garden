package com.stream.garden.system.menu.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.menu.dao.IMenuDao;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author garden
 */
@Service
public class MenuServiceImpl extends AbstractBaseService<Menu, String > implements IMenuService {

    public MenuServiceImpl(IMenuDao iMenuDao) {
        super(iMenuDao);
    }

    @Override
    public int insert(Menu menu) throws ApplicationException {
        // 同一个菜单下两个子菜单的名字不能一样
        Menu paramMenu = new Menu();
        paramMenu.setName(menu.getName());
        paramMenu.setParentId(menu.getParentId());
        if (super.exists(paramMenu)) {
            throw new ApplicationException(SystemExceptionCode.MENU_NAME_REPEAT);
        }
        return super.insert(menu);
    }

    @Override
    public int update(Menu menu) throws ApplicationException {
        // 同一个菜单下两个子菜单的名字不能一样
        Menu paramMenu = new Menu();
        paramMenu.setId(menu.getId());
        paramMenu.setName(menu.getName());
        paramMenu.setParentId(menu.getParentId());
        if (super.exists(paramMenu)) {
            throw new ApplicationException(SystemExceptionCode.MENU_NAME_REPEAT);
        }
        return super.update(menu);
    }

    @Override
    public int delete(String... strings) throws ApplicationException {
        if (ArrayUtils.isEmpty(strings)) {
            return 0;
        }
        for (String id : strings) {
            Menu paramMenu = new Menu();
            paramMenu.setParentId(id);
            if (super.exists(paramMenu)) {
                throw new ApplicationException(SystemExceptionCode.MENU_DELETE_EXCEPTION);
            }
        }
        return super.delete(strings);
    }
}
