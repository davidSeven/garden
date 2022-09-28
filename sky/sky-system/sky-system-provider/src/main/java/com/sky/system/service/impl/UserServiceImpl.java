package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.dao.utils.PageHelpUtil;
import com.sky.framework.dao.utils.WrappersUtil;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.SystemInterface;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.model.User;
import com.sky.system.dao.UserDao;
import com.sky.system.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        this.exists(user);
        return super.save(user);
    }

    @CacheEvict(value = {SystemInterface.SERVICE + ":User:Name"}, key = "#dto.code", condition = "#dto.code != null")
    @Override
    public boolean update(UserDto dto) {
        User user = BeanHelpUtil.convertDto(dto, User.class);
        this.exists(user);
        return super.updateById(user);
    }

    private void exists(User user) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getCode, user.getCode());
        if (null != user.getId()) {
            queryWrapper.ne(User::getId, user.getId());
        }
        if (super.count(queryWrapper) > 0) {
            throw new CommonException(500, "system.user.codeExists", user.getCode());
        }
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

    @Cacheable(value = {SystemInterface.SERVICE + ":User:Name"}, key = "#code", condition = "#code != null")
    @Override
    public String getNameByCode(String code) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class);
        queryWrapper.eq(User::getCode, code);
        queryWrapper.last("limit 1");
        User user = super.getOne(queryWrapper);
        if (null != user) {
            return user.getName();
        }
        return null;
    }
}
