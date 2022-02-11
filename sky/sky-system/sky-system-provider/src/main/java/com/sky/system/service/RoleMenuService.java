package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.RoleMenu;

public interface RoleMenuService extends IService<RoleMenu> {

    void deleteByRoleId(Long roleId);
}
