package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.Menu;
import com.sky.system.dao.MenuDao;
import com.sky.system.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @date 2020-12-16 016 11:37
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Transactional
    @Override
    public boolean save(Menu entity) {
        boolean b = super.save(entity);
        Long parentId = entity.getParentId();
        if (b && null != parentId) {
            // 子级数量+1
            super.baseMapper.increaseChildrenSize(parentId);
        }
        return b;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable id) {
        Menu menu = super.getById(id);
        if (null != menu) {
            boolean b = super.removeById(id);
            Long parentId = menu.getParentId();
            if (b && null != parentId) {
                // 子级数量-1
                super.baseMapper.decreaseChildrenSize(parentId);
            }
        }
        return false;
    }
}
