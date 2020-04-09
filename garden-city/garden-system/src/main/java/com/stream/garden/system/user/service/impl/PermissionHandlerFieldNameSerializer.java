package com.stream.garden.system.user.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.web.interceptor.HandlerFieldNameSerializer;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author garden
 * @date 2020-04-09 9:24
 */
@Component
public class PermissionHandlerFieldNameSerializer implements HandlerFieldNameSerializer {

    private Map<String, String> keyValueMap = new HashMap<>();
    @Autowired
    private IUserService userService;

    @Override
    public String getName(String code) {
        if (StringUtils.isNotEmpty(code)) {
            if (keyValueMap.containsKey(code)) {
                return keyValueMap.get(code);
            } else {
                String value = null;
                User user = null;
                try {
                    user = this.userService.get(code);
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
                if (null != user) {
                    value = user.getName();
                }
                if (keyValueMap.size() > 100) {
                    keyValueMap.clear();
                }
                keyValueMap.put(code, value);
                return value;
            }
        }
        return null;
    }
}
