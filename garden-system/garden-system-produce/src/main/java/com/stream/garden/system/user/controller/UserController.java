package com.stream.garden.system.user.controller;

import com.stream.garden.framework.api.model.Result;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 * @date 2019-06-19 13:49
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> insert(User user) {
        return new Result<Integer>().setData(userService.insert(user)).ok();
    }

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "system/user/list";
    }
}
