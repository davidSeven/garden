package com.stream.garden.lookup.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.validator.groups.ValidationSaveGroup;
import com.stream.garden.framework.validator.groups.ValidationUpdateGroup;
import com.stream.garden.lookup.exception.LookupExceptionCode;
import com.stream.garden.lookup.model.Lookup;
import com.stream.garden.lookup.service.ILookupService;
import com.stream.garden.lookup.vo.LookupVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 */
@Controller
@RequestMapping("/lookup/lookup")
public class LookupController {

    private Logger logger = LoggerFactory.getLogger(LookupController.class);

    @Autowired
    private ILookupService lookupService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "lookup/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "lookup/edit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Lookup>> pageList(LookupVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Lookup>>().setData(lookupService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(@Validated({ValidationSaveGroup.class}) Lookup lookup) {
        try {
            return new Result<Integer>().setData(lookupService.insert(lookup)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, LookupExceptionCode.LOOKUP_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(@Validated({ValidationUpdateGroup.class}) Lookup lookup) {
        try {
            return new Result<Integer>().ok().setData(lookupService.update(lookup));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, LookupExceptionCode.LOOKUP_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Lookup lookup) {
        try {
            return new Result<Integer>().ok().setData(lookupService.delete(lookup.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
