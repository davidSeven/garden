package com.sky.system.utils;

import com.sky.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("InternalUserNameJsonIntensifyConvert")
public class InternalUserNameJsonIntensifyConvert extends AbstractUserNameJsonIntensifyConvert {

    @Autowired
    private UserService userService;

    @Override
    public String getName(String value) {
        return this.userService.getNameByCode(value);
    }

}
