package com.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @date 2020-09-22 022 15:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
