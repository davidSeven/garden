package com.stream.garden.system.menu.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.menu.dao.IMenuDao;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 */
@Service
public class MenuServiceImpl extends AbstractBaseService<Menu, String > implements IMenuService {

    public MenuServiceImpl(IMenuDao iMenuDao) {
        super(iMenuDao);
    }
}
