package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.Menu;
import com.sky.system.dao.MenuDao;
import com.sky.system.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @date 2020-12-16 016 11:37
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
}
