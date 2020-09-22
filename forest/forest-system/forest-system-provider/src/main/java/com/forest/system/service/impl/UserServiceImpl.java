package com.forest.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forest.system.dao.UserDao;
import com.forest.system.model.User;
import com.forest.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @date 2020-09-22 022 15:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
