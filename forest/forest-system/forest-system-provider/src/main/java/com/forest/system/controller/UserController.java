package com.forest.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import com.forest.system.service.UserRemoteService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020-09-23 023 9:11
 */
@RestController
public class UserController implements UserRemoteService {

    @Override
    public ResponseDto<Boolean> save(@RequestBody UserDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> update(@RequestBody UserDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Page<UserDto>> page(@RequestBody UserDto dto) {
        return null;
    }
}
