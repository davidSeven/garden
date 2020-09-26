package com.stream.garden.system.login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Context;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.util.ContextUtil;
import com.stream.garden.framework.util.VerifyCodeUtils;
import com.stream.garden.framework.web.config.GlobalConfig;
import com.stream.garden.framework.web.constant.GlobalConstant;
import com.stream.garden.framework.web.util.IPUtil;
import com.stream.garden.framework.web.util.JwtHelper;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.login.config.LoginConfig;
import com.stream.garden.system.login.service.ILoginService;
import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.menu.vo.MenuVO;
import com.stream.garden.system.user.bo.PermissionBO;
import com.stream.garden.system.user.bo.UserBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author garden
 * @date 2019-06-22 16:05
 */
@Api(tags = "用户登录 - 接口")
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    /**
     * 用户权限缓存key
     */
    public static final String USER_CACHE_PERMISSION_KEY = "garden:user:permission:";
    /**
     * 用户缓存key
     */
    private static final String USER_CACHE_KEY = "garden:user:";
    /**
     * 用户菜单缓存key
     */
    private static final String USER_CACHE_MENU_KEY = "garden:user:menu:";
    /**
     * 登录token
     */
    private static final String LOGIN_TOKEN = "login_token";
    private static final String LOGIN_TOKEN_KEY = "garden:login:";
    private static final String LOGIN_VERIFY_CODE_TOKEN_KEY = "garden:login:verifyCode:";
    /**
     * 用户缓存时间，单位：秒
     */
    private static final int USER_CACHE_TIME = 24 * 60 * 60;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private LoginConfig loginConfig;
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "获取登录标识")
    @GetMapping(value = "/loginToken")
    public Result<String> loginToken(HttpServletRequest request) {
        try {
            // 已经登录了
            if (JwtHelper.isLogin(request, globalConfig.getJwt().getBase64Secret())) {
                return new Result<String>().ok();
            }
            // 获取登录Token
            String loginToken = getLoginToken();
            return new Result<String>().ok().setData(loginToken);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Result<String>().ok();
    }

    @ApiOperation(value = "安全验证")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @PostMapping(value = "/safetyCheck")
    public Result<Integer> safetyCheck(HttpServletRequest request) {
        try {
            // 从request header中获取登录token
            String loginToken = getLoginTokenByRequest(request);
            if (validLoginToken(loginToken)) {
                // 登录token验证失败，需要重新获取登录token
                return new Result<Integer>().ok().setData(-1);
            }
            int check;
            if ("none".equals(loginConfig.getSafetyType())) {
                // 不需要验证
                check = 0;
            } else if ("need".equals(loginConfig.getSafetyType())) {
                // 不检测，需要验证
                check = 1;
            } else if ("normal".equals(loginConfig.getSafetyType())) {
                // 正常检测验证
                String username = request.getParameter("username");
                String ip = IPUtil.getIpAddress(request);
                if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(ip)) {
                    // 返回1需要验证码
                    check = loginService.safetyCheck(username, ip);
                } else {
                    // 用户名，或ip不存在，需要验证码
                    check = 1;
                }
            } else {
                // 其它情况，暂时不需要验证
                check = 0;
            }
            // 1需要验证码
            if (1 == check) {
                // 验证码不要token，直接存redis。添加一个需要验证码的标识token
                // 设置一个key
                redisTemplate.opsForValue().set(LOGIN_VERIFY_CODE_TOKEN_KEY + loginToken, "", 3600, TimeUnit.SECONDS);
            } else {
                // 不需要验证，删除token
                redisTemplate.delete(LOGIN_VERIFY_CODE_TOKEN_KEY + loginToken);
            }
            return new Result<Integer>().ok().setData(check);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping(value = "/verifyCode")
    public Result<String> verifyCode(HttpServletRequest request) {
        try {
            // 验证login token是否存在
            String loginToken = getLoginTokenByRequest(request);
            if (validLoginToken(loginToken)) {
                return new Result<String>().ok();
            }
            // 1.获取验证码
            int verifySize = 4 + VerifyCodeUtils.randomInt(2);
            String verifyCode = VerifyCodeUtils.generateVerifyCode(verifySize);
            // 2.验证码存入redis中
            redisTemplate.opsForValue().set(LOGIN_TOKEN_KEY + loginToken, verifyCode, 3600, TimeUnit.SECONDS);
            // 3.返回验证码
            return new Result<String>().ok().setData(verifyCode);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Result<String>().ok();
    }

    @SuppressWarnings({"all"})
    private boolean hasVerify(String loginToken) {
        return redisTemplate.hasKey(LOGIN_VERIFY_CODE_TOKEN_KEY + loginToken);
    }

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "verifyCode", value = "验证码")
    })
    @PostMapping(value = "/login")
    public Result<String> login(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        // 验证login token是否存在
        String loginToken = getLoginTokenByRequest(request);
        if (validLoginToken(loginToken)) {
            Result<String> result = new Result<>();
            result.setCode(500);
            result.setMsg("login exception!");
            return result;
        }
        // 判断是否需要校验验证码
        if (hasVerify(loginToken)) {
            // 从参数中获取验证码
            String verifyCode = request.getParameter("verifyCode");
            if (null == verifyCode) {
                Result<String> result = new Result<>();
                result.setCode(500);
                result.setMsg("wrong verify code!");
                return result;
            }
            // 获取验证码信息
            Object loginTokenValue = redisTemplate.opsForValue().get(LOGIN_TOKEN_KEY + loginToken);
            // 判断验证码是否正确
            if (!String.valueOf(loginTokenValue).equalsIgnoreCase(verifyCode)) {
                Result<String> result = new Result<>();
                result.setCode(500);
                result.setMsg("wrong verify code!");
                return result;
            }
        }
        // 获取登录参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            Result<String> result = new Result<>();
            result.setCode(500);
            result.setMsg("username or password cannot be empty!");
            return result;
        }
        logger.debug("获取登录参数，耗时:{}", (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        try {
            // 登录
            UserBO userBO = loginService.login(username, password);
            logger.debug("用户验证，耗时:{}", (System.currentTimeMillis() - startTime));
            startTime = System.currentTimeMillis();
            // 用户不存在/密码错误/无可用角色
            if (null == userBO) {
                Result<String> result = new Result<>();
                result.setCode(500);
                result.setMsg("wrong username or password!");
                return result;
            }
            // 判断用户是否可以登录
            if (SystemConstant.USER_STATE_DISABLED.equals(userBO.getState())) {
                Result<String> result = new Result<>();
                result.setCode(500);
                result.setMsg("user has been disabled!");
                return result;
            } else if (SystemConstant.USER_STATE_LOCKED.equals(userBO.getState())) {
                Result<String> result = new Result<>();
                result.setCode(500);
                result.setMsg("user has been locked!");
                return result;
            }
            // 删除登录token
            redisTemplate.delete(LOGIN_TOKEN_KEY + loginToken);
            // 登录成功，更新最后登录信息
            loginService.updateLastLogin(userBO.getId(), IPUtil.getIpAddress(request), new Date());
            // 创建登录token信息
            String token = JwtHelper.createJWT(userBO.getName(), userBO.getId(), userBO.getCurrentRoleId(), globalConfig.getJwt());
            token = GlobalConstant.HEADER_AUTHORIZATION_BEARER + token;
            logger.debug("登录验证，耗时:{}", (System.currentTimeMillis() - startTime));
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
            reloadPermissionSet(userBO.getId(), userBO.getCurrentRoleId());
            return new Result<String>().setData(token).ok();
        } catch (Exception e) {
            logger.error("login exception", e);
        }
        Result<String> result = new Result<>();
        result.setCode(500);
        result.setMsg("login exception!");
        return result;
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping(value = "/getUserInfo")
    public Result<UserBO> getUserInfo() {
        try {
            String userId = ContextUtil.getUserId();
            String roleId = ContextUtil.getRoleId();
            UserBO userBO = getUser(userId);
            if (null != userBO) {
                // 菜单
                userBO.setMenuList(getMenuList(userId, roleId));
                // 权限
                userBO.setPermissionSet(getPermissionSet(userId, roleId));
                return new Result<UserBO>().ok().setData(userBO);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        Result<UserBO> result = new Result<>();
        result.setCode(500);
        result.setMsg("Failed to obtain user information!");
        return result;
    }

    @ApiOperation(value = "登出")
    @GetMapping(value = "/logout")
    public Result<String> logout() {
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
                return new Result<String>().ok();
            }
        } catch (Exception e) {
            logger.error("logout exception", e);
        }
        Result<String> result = new Result<>();
        result.setCode(500);
        result.setMsg("logout exception!");
        return result;

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
                redisTemplate.opsForValue().set(USER_CACHE_KEY + userBO.getId(), JSON.toJSONString(userBO), USER_CACHE_TIME, TimeUnit.SECONDS);
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
                userBO = JSON.parseObject(object.toString(), UserBO.class);
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
                redisTemplate.opsForValue().set(USER_CACHE_MENU_KEY + userId, JSON.toJSONString(menuList), USER_CACHE_TIME, TimeUnit.SECONDS);
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
                menuList = JSON.parseObject(object.toString(), new TypeReference<List<MenuVO>>() {
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
     * @return set
     */
    private Set<String> reloadPermissionSet(String userId, String roleId) {
        try {
            // 加载权限
            List<PermissionBO> permissionList = loginService.getPermissionByRoleId(roleId);
            // 改成set
            Set<String> permissionSet = new HashSet<>();
            if (null != permissionList) {
                for (PermissionBO bo : permissionList) {
                    permissionSet.add(bo.getValue());
                }
                // 放入到缓存中
                redisTemplate.opsForValue().set(USER_CACHE_PERMISSION_KEY + userId, JSON.toJSONString(permissionSet), USER_CACHE_TIME, TimeUnit.SECONDS);
            }
            return permissionSet;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new HashSet<>();
        }
    }

    /**
     * 获取角色权限信息
     *
     * @param userId userId
     * @param roleId roleId
     * @return set
     */
    private Set<String> getPermissionSet(String userId, String roleId) {
        try {
            Set<String> permissionSet;
            Object object = redisTemplate.opsForValue().get(USER_CACHE_PERMISSION_KEY + userId);
            if (null != object) {
                permissionSet = JSON.parseObject(object.toString(), new TypeReference<Set<String>>() {
                });
            } else {
                // 重新加载
                permissionSet = reloadPermissionSet(userId, roleId);
            }
            return permissionSet;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new HashSet<>();
        }
    }

    /**
     * 获取登录token
     */
    private String getLoginToken() {
        String loginToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(LOGIN_VERIFY_CODE_TOKEN_KEY + loginToken, "", 3600, TimeUnit.SECONDS);
        return loginToken;
    }

    /**
     * 更新登录token有效时间
     *
     * @param loginToken loginToken
     */
    private void refreshLoginToken(String loginToken) {
        redisTemplate.opsForValue().set(LOGIN_VERIFY_CODE_TOKEN_KEY + loginToken, "", 3600, TimeUnit.SECONDS);
    }

    /**
     * 验证登录token是否有效
     *
     * @param loginToken loginToken
     * @return boolean
     */
    private boolean validLoginToken(String loginToken) {
        if (StringUtils.isEmpty(loginToken)) {
            return true;
        }
        if (!hasVerify(loginToken)) {
            return true;
        }
        // 更新登录token有效时间
        refreshLoginToken(loginToken);
        return false;
    }

    /**
     * 从RequestHeader中获取登录token
     *
     * @param request request
     * @return String
     */
    private String getLoginTokenByRequest(HttpServletRequest request) {
        return request.getHeader(LOGIN_TOKEN);
    }
}
