package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.interceptor.util.IpUtil;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2020-10-29 029 15:28
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "安全检查")
    @PostMapping("/login/safetyCheck")
    public ResponseDto<SafetyCheckDto> safetyCheck(HttpServletRequest request) {
        String clientRealAddress = IpUtil.getClientRealAddress(request);
        return new ResponseDto<SafetyCheckDto>().ok().setData(this.loginService.safetyCheck(clientRealAddress));
    }
}
