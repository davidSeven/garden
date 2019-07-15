package com.stream.garden.system.menu.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.web.annotation.Limit;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author garden
 */
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController {
    private final IMenuService menuService;
    private static final String CACHE_NAMES = "system:menu";
    private static final String CACHE_KEY = "'list'";
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        logger.debug(">>>页面跳转：{}", "system/menu/list");
        return "system/menu/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        logger.debug(">>>页面跳转：{}", "system/menu/edit");
        return "system/menu/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @CacheEvict(value = CACHE_NAMES, key = CACHE_KEY)
    public Result<Integer> add(Menu menu) {
        try {
            return new Result<Integer>().ok().setData(menuService.insert(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    @CacheEvict(value = CACHE_NAMES, key = CACHE_KEY)
    public Result<Integer> edit(Menu menu) {
        try {
            return new Result<Integer>().ok().setData(menuService.update(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<Menu> get(Menu menu) {
        try {
            return new Result<Menu>().ok().setData(menuService.get(menu.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/list")
    @ResponseBody
    @Limit(name = "menu", key = "list", prefix = "limit:", period = 100, count = 3)
    @Cacheable(value = CACHE_NAMES, key = CACHE_KEY)
    public Result<List<Menu>> list(Menu menu) {
        try {
            Thread.sleep(300);
            return new Result<List<Menu>>().ok().setData(menuService.list(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    @CacheEvict(value = CACHE_NAMES, key = CACHE_KEY)
    public Result<Integer> delete(Menu menu) {
        try {
            return new Result<Integer>().ok().setData(menuService.delete(menu.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
