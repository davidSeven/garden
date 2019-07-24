package com.stream.garden.system.login.controller;

import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.util.CookieUtil;
import com.stream.garden.framework.web.util.JwtHelper;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author garden
 * @date 2019-06-22 16:05
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "system/index";
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404() {
        return "error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            return "redirect:/";
        }
        return "system/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            return "redirect:/";
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            request.setAttribute("login_error_msg", "username or password cannot be empty");
            return "system/login";
        }
        try {
            // 登录
            User user = loginService.login(username, password);
            // 用户不存在/密码错误
            if (null == user) {
                request.setAttribute("login_error_msg", "wrong username or password");
                return "system/login";
            }
            // 判断用户是否可以登录
            if (SystemConstant.USER_STATE_DISABLED.equals(user.getState())) {
                request.setAttribute("login_error_msg", "user has been disabled");
                return "system/login";
            } else if (SystemConstant.USER_STATE_LOCKED.equals(user.getState())) {
                request.setAttribute("login_error_msg", "user has been locked");
                return "system/login";
            }
            String token = JwtHelper.createJWT(user.getName(), user.getId(), user.getCurrentRoleId(), globalConfig.getJwt());
            token = GlobalConstant.HEADER_AUTHORIZATION_BEARER + token;
            CookieUtil.addCookie(response, GlobalConstant.HEADER_AUTHORIZATION, token, 24 * 60 * 60);
            response.setHeader(GlobalConstant.HEADER_AUTHORIZATION, token);
        } catch (Exception e) {
            logger.error("login exception", e);
            request.setAttribute("login_error_msg", "login exception");
            return "system/login";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.removeCookie(response, GlobalConstant.HEADER_AUTHORIZATION);
        } catch (Exception e) {
            logger.error("logout exception", e);
        }
        return "redirect:/";
    }
}
