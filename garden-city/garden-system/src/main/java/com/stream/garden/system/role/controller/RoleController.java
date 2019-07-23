package com.stream.garden.system.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.service.IRoleFunctionService;
import com.stream.garden.system.role.service.IRoleService;
import com.stream.garden.system.role.vo.MenuFunctionVO;
import com.stream.garden.system.role.vo.RoleFunctionVO;
import com.stream.garden.system.role.vo.RoleVO;
import com.stream.garden.system.user.controller.UserController;
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
 * @date 2019-07-08 17:46
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleFunctionService roleFunctionService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        logger.debug(">>>页面跳转：{}", "system/role/list");
        return "system/role/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        logger.debug(">>>页面跳转：{}", "system/role/edit");
        return "system/role/edit";
    }

    @GetMapping(value = "/toSetFunction")
    public String toSetFunction() {
        return "system/role/setFunction";
    }

    @PostMapping(value = "/pageList")
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

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Role role) {
        try {
            return new Result<Integer>().setData(roleService.insert(role)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.USER_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Role role) {
        try {
            return new Result<Integer>().ok().setData(roleService.update(role));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Role role) {
        try {
            return new Result<Integer>().ok().setData(roleService.delete(role.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/getMenuFunction")
    @ResponseBody
    public Result<List<MenuFunctionVO>> getMenuFunction() {
        try {
            return new Result<List<MenuFunctionVO>>().ok().setData(roleFunctionService.getMenuFunction());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/getRoleFunction")
    @ResponseBody
    public Result<List<RoleFunction>> getRoleFunction(RoleFunction roleFunction) {
        try {
            return new Result<List<RoleFunction>>().ok().setData(roleFunctionService.list(roleFunction));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/saveRoleFunction")
    @ResponseBody
    public Result<Integer> saveRoleFunction(String voJson) {
        try {
            RoleFunctionVO vo = JSONObject.parseObject(voJson, RoleFunctionVO.class);
            return new Result<Integer>().ok().setData(roleFunctionService.saveRoleFunction(vo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
