package com.stream.garden.system.role.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
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

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageInfo<Role>> pageList(RoleVO vo) {
        try {
            return new Result<PageInfo<Role>>().setData(roleService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
