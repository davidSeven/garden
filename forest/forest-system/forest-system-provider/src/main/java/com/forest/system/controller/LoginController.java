package com.forest.system.controller;

import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.LoginDto;
import com.forest.system.dto.UserDto;
import com.forest.system.service.LoginService;
import com.forest.system.service.TokenRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2020-10-19 019 13:38
 */
@Api(tags = "用户登录")
@RestController
public class LoginController implements TokenRemoteService {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "loginDto", value = "登录信息", required = true, dataType = "LoginDto")
    @PostMapping("/system/login/login")
    public ResponseDto<String> login(@Validated @RequestBody LoginDto loginDto) {
        return ResponseDto.getSuccessResponseDto(this.loginService.login(loginDto));
    }

    @ApiOperation(value = "登出")
    @PostMapping("/system/login/logout")
    public ResponseDto<String> logout(HttpServletRequest request) {
        this.loginService.logout(request.getHeader("Authorization"));
        return ResponseDto.getSuccessResponseDto(null);
    }

    @ApiOperation(value = "验证")
    @ApiImplicitParam(name = "token", value = "Token信息", required = true, dataType = "String")
    @Override
    public ResponseDto<UserDto> valid(@RequestBody String token) {
        return ResponseDto.getSuccessResponseDto(this.loginService.valid(token));
    }
}
