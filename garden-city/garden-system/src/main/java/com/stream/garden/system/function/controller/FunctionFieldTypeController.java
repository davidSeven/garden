package com.stream.garden.system.function.controller;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.function.model.FunctionFieldType;
import com.stream.garden.system.function.service.IFunctionFieldTypeService;
import com.stream.garden.system.function.vo.FunctionFieldTypeResultVO;
import com.stream.garden.system.function.vo.FunctionFieldTypeSaveVO;
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
 * @date 2020-01-22 11:04
 */
@Controller
@RequestMapping(value = "/system/function-field-type")
public class FunctionFieldTypeController {
    private Logger logger = LoggerFactory.getLogger(FunctionFieldTypeController.class);

    @Autowired
    private IFunctionFieldTypeService functionFieldTypeService;

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "system/functionFieldType/edit";
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Result<Integer> save(String json) {
        try {
            FunctionFieldTypeSaveVO vo = JSONObject.parseObject(json, FunctionFieldTypeSaveVO.class);
            return new Result<Integer>().setData(functionFieldTypeService.save(vo)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.FUNCTION_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/list-view")
    @ResponseBody
    public Result<List<FunctionFieldTypeResultVO>> listView(FunctionFieldType params) {
        try {
            return new Result<List<FunctionFieldTypeResultVO>>().ok().setData(functionFieldTypeService.listView(params));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
