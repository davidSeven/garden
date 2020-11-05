package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.interceptor.util.IpUtil;
import com.sky.framework.utils.VerifyCodeUtils;
import com.sky.system.api.dto.LoginDto;
import com.sky.system.api.dto.SafetyCheckDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.constant.LoginConstant;
import com.sky.system.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @date 2020-10-29 029 15:28
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "安全检查")
    @PostMapping("/login/safetyCheck")
    public ResponseDto<SafetyCheckDto> safetyCheck(HttpServletRequest request) {
        String clientRealAddress = IpUtil.getClientRealAddress(request);
        return new ResponseDto<SafetyCheckDto>().ok().setData(this.loginService.safetyCheck(clientRealAddress));
    }

    @ApiOperation(value = "获取验证码")
    @PostMapping("/login/verifyCode")
    public ResponseDto<String> verifyCode(HttpServletRequest request) {
        // dto中不传，从header中获取
        String vcToken = request.getHeader(LoginConstant.VERIFY_CODE_TOKEN);
        if (StringUtils.isEmpty(vcToken)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        return new ResponseDto<String>().ok().setData(this.loginService.verifyCode(vcToken));
    }

    @ApiOperation(value = "获取验证码")
    @PostMapping("/login/verifyCodeResponse")
    public void verifyCodeResponse(HttpServletRequest request, HttpServletResponse response) {
        // dto中不传，从header中获取
        String vcToken = request.getHeader(LoginConstant.VERIFY_CODE_TOKEN);
        if (StringUtils.isEmpty(vcToken)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        OutputStream os = null;
        try {
            String verifyCode = this.loginService.verifyCode(vcToken);
            os = response.getOutputStream();
            // 3.返回验证码图片
            VerifyCodeUtils.outputImage(100, 40, os, verifyCode);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "dto", value = "登录信息", required = true, dataType = "LoginDto")
    @PostMapping("/login/login")
    public ResponseDto<UserLoginDto> login(@RequestBody @Validated LoginDto dto, HttpServletRequest request) {
        // dto中不传，从header中获取
        String vcToken = request.getHeader(LoginConstant.VERIFY_CODE_TOKEN);
        if (StringUtils.isEmpty(vcToken)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        // 登录时请求的token
        String lrTime = request.getHeader(LoginConstant.LOGIN_REQUEST_TIME);
        this.validLoginRequestTime(lrTime);
        // 获取ip
        String ip = IpUtil.getClientRealAddress(request);
        dto.setIp(ip);
        // 处理请求数据
        dto.setVcToken(vcToken);
        return new ResponseDto<UserLoginDto>().ok().setData(this.loginService.login(dto));
    }

    @ApiOperation(value = "登出")
    @PostMapping("/login/logout")
    public void logout(HttpServletRequest request) {
        // 从header中获取token
        String token = request.getHeader(LoginConstant.AUTHORIZATION_TOKEN);
        this.loginService.logout(token);
    }

    private void validLoginRequestTime(String lrTime) {
        // 登录请求超过1分钟，不予处理
        if (StringUtils.isEmpty(lrTime)) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
        try {
            long loginRequestTime = Long.parseLong(lrTime);
            long timeMillis = System.currentTimeMillis();
            if (timeMillis - loginRequestTime > (60 * 1000)) {
                throw new RuntimeException("登录请求超过1分钟，视为无效请求");
            }
        } catch (Exception e) {
            throw new CommonException(500, "登录异常，请刷新后再操作");
        }
    }
}
