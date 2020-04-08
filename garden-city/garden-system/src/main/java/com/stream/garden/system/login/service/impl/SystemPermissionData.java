package com.stream.garden.system.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.framework.util.ContextUtil;
import com.stream.garden.framework.web.permission.IPermissionData;
import com.stream.garden.framework.web.permission.Permission;
import com.stream.garden.system.constant.SystemConstant;
import com.stream.garden.system.function.model.Function;
import com.stream.garden.system.function.service.IFunctionService;
import com.stream.garden.system.login.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author garden
 * @date 2019-09-28 15:40
 */
@Component
public class SystemPermissionData implements IPermissionData {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IFunctionService functionService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Permission> getSystemPermissionList() {
        try {
            Function paramFunction = new Function();
            paramFunction.setState(SystemConstant.STATE_1);
            List<Function> functionList = functionService.list(paramFunction);
            if (CollectionUtil.isNotEmpty(functionList)) {
                List<Permission> permissionList = new ArrayList<>();
                for (Function function : functionList) {
                    permissionList.add(new Permission(function.getUrl(), function.getCode()));
                }
                return permissionList;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean validPermission(String urlCode) {
        if (null == urlCode) {
            return true;
        }
        String userId = ContextUtil.getUserId();
        if (null == userId) {
            return false;
        }
        Set<String> permissionSet = null;
        Object object = redisTemplate.opsForValue().get(LoginController.USER_CACHE_PERMISSION_KEY + userId);
        if (null != object) {
            permissionSet = JSONObject.parseObject(object.toString(), new TypeReference<Set<String>>() {
            });
        }
        if (null != permissionSet) {
            return permissionSet.contains(urlCode);
        }
        return false;
    }
}
