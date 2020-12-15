package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;
import com.sky.system.api.model.OnlineUser;
import com.sky.system.api.remote.AuthenticationRemoteService;
import com.sky.system.service.LoginService;
import com.sky.system.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @date 2020-12-15 015 13:42
 */
@Api(tags = "鉴权信息")
@RestController
public class AuthenticationController implements AuthenticationRemoteService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private OnlineUserService onlineUserService;

    @ApiOperation(value = "验证")
    @ApiImplicitParam(name = "dto", value = "验证信息", required = true, dataType = "VerificationDto")
    @Override
    public ResponseDto<UserLoginDto> verification(@RequestBody VerificationDto dto) {
        return new ResponseDto<>(null, this.loginService.verification(dto)).ok();
    }

    @ApiOperation(value = "访问")
    @ApiImplicitParam(name = "dto", value = "访问信息", required = true, dataType = "AccessDto")
    @Override
    public ResponseDto<Void> access(@RequestBody AccessDto dto) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setLoginToken(dto.getAuthorization());
        onlineUser.setLastVisitDate(new Date());
        onlineUserService.visitOnlineUser(onlineUser);
        return new ResponseDto<Void>().ok();
    }
}
