package com.stream.garden.system.menu.controller;

import com.stream.garden.framework.api.exception.AppCode;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 */
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转列表页面
     * @return 页面路径
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        logger.debug(">>>页面跳转：{}", "system/menu/list");
        return "system/menu/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> add(Menu menu) {
        try {
            return new Result<Integer>().ok().setData(menuService.insert(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(SystemExceptionCode.MENU_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> edit(Menu menu) {
        try {
            return new Result<Integer>().ok().setData(menuService.update(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(SystemExceptionCode.MENU_EDIT_EXCEPTION.getAppCode(e));
        }
    }
}
