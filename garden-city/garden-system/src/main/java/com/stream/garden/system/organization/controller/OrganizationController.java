package com.stream.garden.system.organization.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.organization.model.Organization;
import com.stream.garden.system.organization.service.IOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author garden
 * @date 2019/7/21 11:38
 */
@Controller
@RequestMapping(value = "/system/organization")
public class OrganizationController {
    private Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "system/organization/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "system/organization/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Organization organization) {
        try {
            return new Result<Integer>().ok().setData(organizationService.insert(organization));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.ORGANIZATION_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Organization organization) {
        try {
            return new Result<Integer>().ok().setData(organizationService.update(organization));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.ORGANIZATION_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<Organization> get(Organization organization) {
        try {
            return new Result<Organization>().ok().setData(organizationService.get(organization.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public Result<List<Organization>> list(Organization organization) {
        try {
            organization.asOrderBy("SORTS", OrderByObj.ASC);
            return new Result<List<Organization>>().ok().setData(organizationService.list(organization));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Organization organization) {
        try {
            return new Result<Integer>().ok().setData(organizationService.delete(organization.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
