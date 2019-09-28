package com.stream.garden.system.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
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
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2019-06-22 16:05
 */
@Controller
public class LoginController {
    /**
     * 登录错误信息key
     */
    private static final String LOGIN_ERROR_MSG_KEY = "login_error_msg";
    /**
     * 用户缓存key
     */
    private static final String USER_CACHE_KEY = "garden:user:";
    /**
     * 用户菜单缓存key
     */
    private static final String USER_CACHE_MENU_KEY = "garden:user:menu:";
    /**
     * 用户权限缓存key
     */
    private static final String USER_CACHE_PERMISSION_KEY = "garden:user:permission:";
    /**
     * 重定向到首页
     */
    private static final String REDIRECT_TO_INDEX_KEY = "redirect:/";
    /**
     * 跳转到首页
     */
    private static final String TO_SYSTEM_INDEX_KEY = "system/index";
    /**
     * 跳转到登录页
     */
    private static final String TO_SYSTEM_LOGIN_KEY = "system/login";
    /**
     * 用户缓存时间，单位：秒
     */
    private static final int USER_CACHE_TIME = 24 * 60 * 60;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        try {
            // 加载用户信息
            Context context = ContextUtil.getContext();
            if (null != context) {
                UserBO userBO = (UserBO) context.getUser();
                List<MenuVO> menuList = getMenuList(context.getUserId(), context.getRoleId());
                List<PermissionBO> permissionList = getPermissionList(context.getUserId(), context.getRoleId());
                if (null == userBO) {
                    userBO = getUser(context.getUserId());
                }
                // 判断用户信息是否存在
                if (null != userBO) {
                    request.setAttribute("user", userBO);
                }
                if (null != menuList) {
                    request.setAttribute("menuList", menuList);
                }
                if (null != permissionList) {
                    request.setAttribute("permissionList", permissionList);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return TO_SYSTEM_INDEX_KEY;
    }

    @GetMapping(value = "/error404")
    public String error404() {
        return "error";
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request) {
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            return REDIRECT_TO_INDEX_KEY;
        }
        return TO_SYSTEM_LOGIN_KEY;
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
            return REDIRECT_TO_INDEX_KEY;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            request.setAttribute(LOGIN_ERROR_MSG_KEY, "username or password cannot be empty");
            return TO_SYSTEM_LOGIN_KEY;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("获取登录参数，耗时:{}", (System.currentTimeMillis() - startTime));
            startTime = System.currentTimeMillis();
        }
        try {
            // 登录
            UserBO userBO = loginService.login(username, password);
            if (logger.isDebugEnabled()) {
                logger.debug("用户验证，耗时:{}", (System.currentTimeMillis() - startTime));
                startTime = System.currentTimeMillis();
            }
            // 用户不存在/密码错误/无可用角色
            if (null == userBO) {
                request.setAttribute(LOGIN_ERROR_MSG_KEY, "wrong username or password");
                return TO_SYSTEM_LOGIN_KEY;
            }
            // 判断用户是否可以登录
            if (SystemConstant.USER_STATE_DISABLED.equals(userBO.getState())) {
                request.setAttribute(LOGIN_ERROR_MSG_KEY, "user has been disabled");
                return TO_SYSTEM_LOGIN_KEY;
            } else if (SystemConstant.USER_STATE_LOCKED.equals(userBO.getState())) {
                request.setAttribute(LOGIN_ERROR_MSG_KEY, "user has been locked");
                return TO_SYSTEM_LOGIN_KEY;
            }
            // 登录成功，更新最后登录信息
            loginService.updateLastLogin(userBO.getId(), IPUtil.getIpAddress(request), new Date());
            // 创建登录token信息
            String token = JwtHelper.createJWT(userBO.getName(), userBO.getId(), userBO.getCurrentRoleId(), globalConfig.getJwt());
            token = GlobalConstant.HEADER_AUTHORIZATION_BEARER + token;
            CookieUtil.addCookie(response, GlobalConstant.HEADER_AUTHORIZATION, token, USER_CACHE_TIME);
            response.setHeader(GlobalConstant.HEADER_AUTHORIZATION, token);
            if (logger.isDebugEnabled()) {
                logger.debug("登录验证，耗时:{}", (System.currentTimeMillis() - startTime));
            }
            // 设置上下文信息
            Context context = new Context();
            context.setUserId(userBO.getId());
            context.setRoleId(userBO.getCurrentRoleId());
            ContextUtil.setContext(context);
            // 加载用户信息
            reloadUser(userBO.getId());
            // 加载用户菜单信息
            reloadMenuList(userBO.getId(), userBO.getCurrentRoleId());
            // 加载用户权限信息
            reloadPermissionList(userBO.getId(), userBO.getCurrentRoleId());
        } catch (Exception e) {
            logger.error("login exception", e);
            request.setAttribute(LOGIN_ERROR_MSG_KEY, "login exception");
            return TO_SYSTEM_LOGIN_KEY;
        }
        return REDIRECT_TO_INDEX_KEY;
    }

    /**
     * 加载用户信息
     *
     * @param userId userId
     * @return UserBO
     */
    private UserBO reloadUser(String userId) {
        try {
            // 查询用户信息
            UserBO userBO = loginService.getByUserId(userId);
            if (null != userBO) {
                // 放入到缓存中
                redisTemplate.opsForValue().set(USER_CACHE_KEY + userBO.getId(), JSONObject.toJSONString(userBO), USER_CACHE_TIME, TimeUnit.SECONDS);
            }
            return userBO;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取用户信息
     *
     * @param userId userId
     * @return UserBO
     */
    private UserBO getUser(String userId) {
        try {
            UserBO userBO;
            Object object = redisTemplate.opsForValue().get(USER_CACHE_KEY + userId);
            if (null != object) {
                userBO = JSONObject.parseObject(object.toString(), UserBO.class);
            } else {
                // 重新加载
                userBO = reloadUser(userId);
            }
            return userBO;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 加载角色菜单信息
     *
     * @param userId userId
     * @param roleId roleId
     * @return list
     */
    private List<MenuVO> reloadMenuList(String userId, String roleId) {
        try {
            // 加载菜单
            List<MenuVO> menuList = this.menuService.getRoleMenu(roleId);
            if (null != menuList) {
                // 放入到缓存中
                redisTemplate.opsForValue().set(USER_CACHE_MENU_KEY + userId, JSONObject.toJSONString(menuList), USER_CACHE_TIME, TimeUnit.SECONDS);
            }
            return menuList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取角色菜单信息
     *
     * @param userId userId
     * @param roleId roleId
     * @return list
     */
    private List<MenuVO> getMenuList(String userId, String roleId) {
        try {
            List<MenuVO> menuList;
            Object object = redisTemplate.opsForValue().get(USER_CACHE_MENU_KEY + userId);
            if (null != object) {
                menuList = JSONObject.parseObject(object.toString(), new TypeReference<List<MenuVO>>() {
                });
            } else {
                // 重新加载
                menuList = reloadMenuList(userId, roleId);
            }
            return menuList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * 加载角色权限信息
     *
     * @param userId userId
     * @param roleId roleId
     * @return list
     */
    private List<PermissionBO> reloadPermissionList(String userId, String roleId) {
        try {
            // 加载权限
            List<PermissionBO> permissionList = loginService.getPermissionByRoleId(roleId);
            if (null != permissionList) {
                // 放入到缓存中
                redisTemplate.opsForValue().set(USER_CACHE_PERMISSION_KEY + userId, JSONObject.toJSONString(permissionList), USER_CACHE_TIME, TimeUnit.SECONDS);
            }
            return permissionList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取角色权限信息
     *
     * @param userId userId
     * @param roleId roleId
     * @return list
     */
    private List<PermissionBO> getPermissionList(String userId, String roleId) {
        try {
            List<PermissionBO> permissionList;
            Object object = redisTemplate.opsForValue().get(USER_CACHE_PERMISSION_KEY + userId);
            if (null != object) {
                permissionList = JSONObject.parseObject(object.toString(), new TypeReference<List<PermissionBO>>() {
                });
            } else {
                // 重新加载
                permissionList = reloadPermissionList(userId, roleId);
            }
            return permissionList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            Context context = ContextUtil.getContext();
            if (null != context) {
                List<String> deleteKeys = new ArrayList<>();
                // 删除用户缓存信息
                deleteKeys.add(USER_CACHE_KEY + context.getUserId());
                // 删除用户菜单缓存信息
                deleteKeys.add(USER_CACHE_MENU_KEY + context.getUserId());
                // 删除用户权限缓存信息
                deleteKeys.add(USER_CACHE_PERMISSION_KEY + context.getUserId());
                redisTemplate.delete(deleteKeys);
            }
            // 删除cookie
            CookieUtil.removeCookie(response, GlobalConstant.HEADER_AUTHORIZATION);
        } catch (Exception e) {
            logger.error("logout exception", e);
        }
        return REDIRECT_TO_INDEX_KEY;
    }
}
