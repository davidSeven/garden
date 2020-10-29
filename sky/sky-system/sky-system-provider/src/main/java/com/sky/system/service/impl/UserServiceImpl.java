package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.dao.utils.PageHelpUtil;
import com.sky.framework.dao.utils.WrappersUtil;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.model.User;
import com.sky.system.dao.UserDao;
import com.sky.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @date 2020-10-28 028 13:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public UserDto get(Long id) {
        return BeanHelpUtil.convertDto(super.getById(id), UserDto.class);
    }

    @Override
    public boolean save(UserDto dto) {
        User user = BeanHelpUtil.convertDto(dto, User.class);
        return super.save(user);
    }

    @Override
    public boolean update(UserDto dto) {
        User user = BeanHelpUtil.convertDto(dto, User.class);
        return super.updateById(user);
    }

    @Override
    public IPage<UserDto> pageList(UserQueryDto queryDto) {
        IPage<User> page = new Page<>(queryDto.getPageNum(), queryDto.getPageSize());
        // 查询条件
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        WrappersUtil.like(queryWrapper, User::getCode, queryDto.getCode());
        WrappersUtil.like(queryWrapper, User::getName, queryDto.getName());
        WrappersUtil.eq(queryWrapper, User::getState, queryDto.getState());
        // 查询
        IPage<User> iPage = super.page(page, queryWrapper);
        return PageHelpUtil.convertPage(iPage, UserDto.class);
    }
}
