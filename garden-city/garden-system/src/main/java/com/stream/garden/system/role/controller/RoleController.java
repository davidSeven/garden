package com.stream.garden.system.role.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleService;
import com.stream.garden.system.role.vo.RoleVO;
import com.stream.garden.system.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author garden
 * @date 2019-07-08 17:46
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IRoleService roleService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList() {
        logger.debug(">>>页面跳转：{}", "system/role/list");
        return "system/role/list";
    }

    /**
     * 跳转编辑页面
     * @return 页面路径
     */
    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit() {
        logger.debug(">>>页面跳转：{}", "system/role/edit");
        return "system/role/edit";
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageInfo<Role>> pageList(RoleVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            List<OrderByObj> orders = new ArrayList<>();
            orders.add(new OrderByObj("UPDATION_DATE", 1));
            vo.getCriteria().setOrderByClauses(orders);
            return new Result<PageInfo<Role>>().setData(roleService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> add(Role role) {
        try {
            return new Result<Integer>().setData(roleService.insert(role)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.USER_INSERT_EXCEPTION.getAppCode(e));
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> edit(Role role) {
        try {
            return new Result<Integer>().ok().setData(roleService.update(role));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> delete(Role role) {
        try {
            return new Result<Integer>().ok().setData(roleService.delete(role.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
