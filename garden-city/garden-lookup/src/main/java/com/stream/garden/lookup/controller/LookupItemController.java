package com.stream.garden.lookup.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.lookup.exception.LookupExceptionCode;
import com.stream.garden.lookup.model.LookupItem;
import com.stream.garden.lookup.service.ILookupItemService;
import com.stream.garden.lookup.vo.LookupItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 */
@Controller
@RequestMapping("/lookup/lookupItem")
public class LookupItemController {

    private Logger logger = LoggerFactory.getLogger(LookupItemController.class);

    @Autowired
    private ILookupItemService lookupItemService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "lookup/itemList";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "lookup/itemEdit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<LookupItem>> pageList(LookupItemVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<LookupItem>>().setData(lookupItemService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(LookupItem lookupItem) {
        try {
            return new Result<Integer>().setData(lookupItemService.insert(lookupItem)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, LookupExceptionCode.LOOKUP_ITEM_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(LookupItem lookupItem) {
        try {
            return new Result<Integer>().ok().setData(lookupItemService.update(lookupItem));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, LookupExceptionCode.LOOKUP_ITEM_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(LookupItem lookupItem) {
        try {
            return new Result<Integer>().ok().setData(lookupItemService.delete(lookupItem.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
