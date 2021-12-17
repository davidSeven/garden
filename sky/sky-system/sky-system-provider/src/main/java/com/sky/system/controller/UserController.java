package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.remote.UserRemoteService;
import com.sky.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2020-10-28 028 13:49
 */
@Api(tags = "用户信息")
@RestController
public class UserController implements UserRemoteService {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询")
    @ApiImplicitParam(name = "dto", value = "用户信息", required = true, dataType = "UserDto")
    @Override
    public ResponseDto<UserDto> get(@RequestBody UserDto dto,
                                    @RequestParam(value = "code", required = false, defaultValue = "none") String code,
                                    @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        this.logger.info("code:{}, id:{}", code, id);
        return ResponseDto.getSuccessResponseDto(this.userService.get(dto.getId()));
    }

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "dto", value = "用户信息", required = true, dataType = "UserDto")
    @Override
    public ResponseDto<Boolean> save(@RequestBody UserDto dto) {
        return new ResponseDto<Boolean>().ok().setData(this.userService.save(dto));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "dto", value = "用户信息", required = true, dataType = "UserDto")
    @Override
    public ResponseDto<Boolean> update(@RequestBody UserDto dto) {
        return new ResponseDto<Boolean>().ok().setData(this.userService.update(dto));
    }

    @ApiOperation(value = "分页查询")
    @ApiImplicitParam(name = "queryDto", value = "用户查询信息", required = true, dataType = "UserQueryDto")
    @Override
    public ResponseDto<IPage<UserDto>> pageList(@RequestBody UserQueryDto queryDto) {
        return new ResponseDto<IPage<UserDto>>().ok().setData(this.userService.pageList(queryDto));
    }
}
