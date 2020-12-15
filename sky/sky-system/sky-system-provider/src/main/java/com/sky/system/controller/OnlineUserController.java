package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.OnlineUser;
import com.sky.system.api.remote.OnlineUserRemoteService;
import com.sky.system.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @date 2020-12-15 015 11:15
 */
@Api(tags = "在线用户信息")
@RestController
public class OnlineUserController implements OnlineUserRemoteService {

    @Autowired
    private OnlineUserService onlineUserService;

    @ApiOperation(value = "用户访问")
    @ApiImplicitParam(name = "token", value = "token信息", required = true, dataType = "String")
    @Override
    public ResponseDto<Boolean> visit(@RequestBody String token) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setLoginToken(token);
        onlineUser.setLastVisitDate(new Date());
        this.onlineUserService.visitOnlineUser(onlineUser);
        return new ResponseDto<Boolean>().ok();
    }
}
