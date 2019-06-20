package com.stream.garden.system.user.service;

import com.stream.garden.BaseTest;
import com.stream.garden.system.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {

    @Autowired
    private IUserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setName("admin");
        user.setCode("admin");
        user.setState("1");
        Assert.assertEquals(userService.insert(user), 1);
    }
}
