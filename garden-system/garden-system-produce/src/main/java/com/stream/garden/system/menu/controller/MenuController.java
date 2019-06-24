package com.stream.garden.system.menu.controller;

import com.stream.garden.system.menu.service.IMenuService;
import com.stream.garden.system.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author garden
 */
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        return "system/menu/list";
    }
}
