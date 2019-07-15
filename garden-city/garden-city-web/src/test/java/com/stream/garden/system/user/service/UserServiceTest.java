package com.stream.garden.system.user.service;

import com.stream.garden.BaseTest;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.system.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private IUserService userService;

    @Test
    public void insert() throws ApplicationException {
        User user = new User();
        user.setName("admin");
        user.setCode("admin");
        user.setState("1");
        int result = 1;
        Assert.assertEquals("新增失败", userService.insert(user), result);
    }
}
