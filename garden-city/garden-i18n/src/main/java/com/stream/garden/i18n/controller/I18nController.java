package com.stream.garden.i18n.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.i18n.exception.I18nExceptionCode;
import com.stream.garden.i18n.model.I18n;
import com.stream.garden.i18n.service.II18nService;
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
 * @date 2019-10-24 17:17
 */
@Controller
@RequestMapping(value = "/i18n/i18n")
public class I18nController {
    private Logger logger = LoggerFactory.getLogger(I18nController.class);

    private II18nService i18nService;

    @Autowired
    public I18nController(II18nService i18nService) {
        this.i18nService = i18nService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "i18n/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "i18n/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.insert(i18n));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, I18nExceptionCode.I18N_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.update(i18n));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, I18nExceptionCode.I18N_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<I18n> get(I18n i18n) {
        try {
            return new Result<I18n>().ok().setData(i18nService.get(i18n.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(I18n i18n) {
        try {
            return new Result<Integer>().ok().setData(i18nService.delete(i18n.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
