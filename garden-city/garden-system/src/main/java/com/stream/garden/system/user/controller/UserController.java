package com.stream.garden.system.user.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.model.UserRole;
import com.stream.garden.system.user.service.IUserRoleService;
import com.stream.garden.system.user.service.IUserService;
import com.stream.garden.system.user.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        logger.debug(">>>页面跳转：{}", "system/user/list");
        return "system/user/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        logger.debug(">>>页面跳转：{}", "system/user/edit");
        return "system/user/edit";
    }

    /**
     * 跳转设置角色页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toSetRole")
    public String toSetRole() {
        return "system/user/setRole";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<User>> pageList(UserVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            List<OrderByObj> orders = new ArrayList<>();
            orders.add(new OrderByObj("UPDATION_DATE", 1));
            vo.getCriteria().setOrderByClauses(orders);
            return new Result<PageInfo<User>>().setData(userService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(User user) {
        try {
            return new Result<Integer>().setData(userService.insert(user)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.USER_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(User user) {
        try {
            return new Result<Integer>().ok().setData(userService.update(user));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(User user) {
        try {
            return new Result<Integer>().ok().setData(userService.delete(user.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/setRole")
    @ResponseBody
    public Result<Integer> setRole(UserRole userRole) {
        try {
            return new Result<Integer>().ok().setData(userRoleService.setRole(userRole));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
