package com.stream.garden.system.user.controller;

import com.stream.garden.framework.api.exception.AppCode;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> insert(User user) {
        try {
            return new Result<Integer>().setData(userService.insert(user)).ok();
        } catch (Exception e) {
            AppCode appCode = SystemExceptionCode.USER_INSERT_EXCEPTION.getAppCode(e);
            logger.error(">>>" + appCode.getMessage(), e);
            return new Result<>(appCode);
        }
    }

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "system/user/list";
    }
}
