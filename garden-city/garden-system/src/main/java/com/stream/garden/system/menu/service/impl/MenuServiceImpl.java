package com.stream.garden.system.menu.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.menu.dao.IMenuDao;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
        // 设置父级id
        if (StringUtils.isEmpty(menu.getParentId())) {
            menu.setParentId("0");
        }
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
        // paramMenu.setId(menu.getId());
        paramMenu.setName(menu.getName());
        paramMenu.setParentId(menu.getParentId());
        // 根据名字和父级id查询，如果返回的条数大于1，则存在相同的记录
        if (super.baseMapper.exists(paramMenu) > 1) {
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
            // 根据parentId查询记录，如果存在，则存在自己，则不能删除
            if (super.exists(paramMenu)) {
                throw new ApplicationException(SystemExceptionCode.MENU_DELETE_EXCEPTION);
            }
        }
        return super.delete(strings);
    }
}
