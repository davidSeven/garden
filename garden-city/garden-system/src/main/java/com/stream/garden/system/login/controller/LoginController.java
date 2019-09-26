package com.stream.garden.system.login.controller;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.model.Context;
import com.stream.garden.framework.web.util.ContextUtil;
import com.stream.garden.framework.web.util.CookieUtil;
import com.stream.garden.framework.web.util.IPUtil;
import com.stream.garden.framework.web.util.JwtHelper;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.menu.vo.MenuVO;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleService;
import com.stream.garden.system.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        try {
            // 加载用户信息
            Context context = ContextUtil.getContext();
            if (null != context) {
                User user = (User) context.getUser();
                if (null == user) {
                    // 查询用户信息
                    user = loginService.getByUserId(context.getUserId());
                }
                request.setAttribute("user", user);

                // 查询角色名称
                Role role = roleService.get(context.getRoleId());
                request.setAttribute("roleName", role.getName());

                // 加载菜单
                List<MenuVO> menuList = this.menuService.getRoleMenu(context.getRoleId());
                request.setAttribute("menuList", menuList);
                // 加载权限
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        return "system/index";
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404() {
        return "error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            // return "redirect:/";
            return "redirect:/";
        }
        return "system/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            return "redirect:/";
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            request.setAttribute("login_error_msg", "username or password cannot be empty");
            return "system/login";
        }
        if (logger.isDebugEnabled()) {
            logger.debug("获取登录参数，耗时:{}", (System.currentTimeMillis() - startTime));
            startTime = System.currentTimeMillis();
        }
        try {
            // 登录
            User user = loginService.login(username, password);
            if (logger.isDebugEnabled()) {
                logger.debug("用户验证，耗时:{}", (System.currentTimeMillis() - startTime));
                startTime = System.currentTimeMillis();
            }
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

            // 登录成功，更新最后登录信息
            loginService.updateLastLogin(user.getId(), IPUtil.getIpAddress(request), new Date());

            // 测试
            // user.setCurrentRoleId("308560784638578688");

            String token = JwtHelper.createJWT(user.getName(), user.getId(), user.getCurrentRoleId(), globalConfig.getJwt());
            token = GlobalConstant.HEADER_AUTHORIZATION_BEARER + token;
            CookieUtil.addCookie(response, GlobalConstant.HEADER_AUTHORIZATION, token, 24 * 60 * 60);
            response.setHeader(GlobalConstant.HEADER_AUTHORIZATION, token);
            if (logger.isDebugEnabled()) {
                logger.debug("登录验证，耗时:{}", (System.currentTimeMillis() - startTime));
            }

            // 设置上下文信息
            Context context = new Context();
            context.setUser(user);
            context.setUserId(user.getId());
            context.setRoleId(user.getCurrentRoleId());
            ContextUtil.setContext(context);
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
