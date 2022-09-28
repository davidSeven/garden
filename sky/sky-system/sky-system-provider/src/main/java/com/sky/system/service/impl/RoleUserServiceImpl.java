package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.RoleUser;
import com.sky.system.dao.RoleUserDao;
import com.sky.system.service.RoleUserService;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserDao, RoleUser> implements RoleUserService {
}
