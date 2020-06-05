package com.stream.garden.system.number.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.OrderByObj;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.number.model.SerialNumber;
import com.stream.garden.system.number.service.ISerialNumberService;
import com.stream.garden.system.number.vo.GenerateNumberVO;
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
 * @date 2020-06-05 9:16
 */
@Controller
@RequestMapping(value = "/system/number")
public class SerialNumberController {
    private final Logger logger = LoggerFactory.getLogger(SerialNumberController.class);

    @Autowired
    private ISerialNumberService serialNumberService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "system/number/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "system/number/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(SerialNumber serialNumber) {
        try {
            return new Result<Integer>().ok().setData(serialNumberService.insert(serialNumber));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(SerialNumber serialNumber) {
        try {
            return new Result<Integer>().ok().setData(serialNumberService.update(serialNumber));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.MENU_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<SerialNumber> get(SerialNumber serialNumber) {
        try {
            return new Result<SerialNumber>().ok().setData(serialNumberService.get(serialNumber.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/generateNumber")
    @ResponseBody
    public Result<String> generateNumber(GenerateNumberVO vo) {
        try {
            return new Result<String>().ok().setData(serialNumberService.generateNumber(vo.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/generateNumbers")
    @ResponseBody
    public Result<List<String>> generateNumbers(GenerateNumberVO vo) {
        try {
            return new Result<List<String>>().ok().setData(serialNumberService.generateNumbers(vo.getCode(), vo.getNum()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public Result<List<SerialNumber>> list(SerialNumber serialNumber) {
        try {
            serialNumber.asOrderBy("SORTS", OrderByObj.ASC);
            return new Result<List<SerialNumber>>().ok().setData(serialNumberService.list(serialNumber));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(SerialNumber serialNumber) {
        try {
            return new Result<Integer>().ok().setData(serialNumberService.delete(serialNumber.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
