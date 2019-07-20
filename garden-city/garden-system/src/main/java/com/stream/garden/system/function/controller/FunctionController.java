package com.stream.garden.system.function.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.function.model.Function;
import com.stream.garden.system.function.service.IFunctionService;
import com.stream.garden.system.function.vo.FunctionVO;
import com.stream.garden.system.menu.model.Menu;
import com.stream.garden.system.menu.service.IMenuService;
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
 * @date 2019-07-20 15:47
 */
@Controller
@RequestMapping(value = "/system/function")
public class FunctionController {

    private final IFunctionService functionService;
    private final IMenuService menuService;
    private Logger logger = LoggerFactory.getLogger(FunctionController.class);

    @Autowired
    public FunctionController(IFunctionService functionService, IMenuService menuService) {
        this.functionService = functionService;
        this.menuService = menuService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "system/function/list";
    }

    /**
     * 跳转编辑页面
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "system/function/edit";
    }

    @PostMapping(value = "/menuList")
    @ResponseBody
    public Result<List<Menu>> menuList(Menu menu) {
        try {
            menu.asOrderBy("SORTS", OrderByObj.ASC);
            return new Result<List<Menu>>().ok().setData(menuService.list(menu));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Function>> pageList(FunctionVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Function>>().setData(functionService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Function function) {
        try {
            return new Result<Integer>().setData(functionService.insert(function)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.FUNCTION_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Function function) {
        try {
            return new Result<Integer>().ok().setData(functionService.update(function));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.FUNCTION_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Function function) {
        try {
            return new Result<Integer>().ok().setData(functionService.delete(function.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
