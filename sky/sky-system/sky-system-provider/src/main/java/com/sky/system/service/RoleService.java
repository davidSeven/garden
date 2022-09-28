package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.RoleAuthDto;
import com.sky.system.api.model.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<Long> authList(Long id);

    boolean auth(RoleAuthDto dto);
}
