package com.forest.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forest.framework.dto.ResponseDto;
import com.forest.framework.utils.BeanHelpUtil;
import com.forest.system.dto.UserDto;
import com.forest.system.model.User;
import com.forest.system.service.UserRemoteService;
import com.forest.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020-09-23 023 9:11
 */
@RestController
public class UserController implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseDto<Boolean> save(@RequestBody UserDto dto) {
        User user = BeanHelpUtil.convertDto(dto, User.class);
        return ResponseDto.getSuccessResponseDto(this.userService.save(user));
    }

    @Override
    public ResponseDto<Boolean> update(@RequestBody UserDto dto) {
        return null;
    }

    @Override
    public ResponseDto<IPage<User>> page(@RequestBody UserDto dto) {
        IPage<User> iPage = new Page<>(1, 10);
        QueryWrapper<User> queryWrapper = null;
        return ResponseDto.getSuccessResponseDto(this.userService.page(iPage, queryWrapper));
    }
}
