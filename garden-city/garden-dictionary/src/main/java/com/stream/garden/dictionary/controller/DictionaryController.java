package com.stream.garden.dictionary.controller;

import com.stream.garden.dictionary.exception.DictionaryExceptionCode;
import com.stream.garden.dictionary.model.Dictionary;
import com.stream.garden.dictionary.service.IDictionaryService;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.OrderByObj;
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
 * @date 2019-09-29 13:40
 */
@Controller
@RequestMapping(value = "/dictionary/dictionary")
public class DictionaryController {
    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    private IDictionaryService dictionaryService;

    @Autowired
    public DictionaryController(IDictionaryService menuService) {
        this.dictionaryService = menuService;
    }

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "dictionary/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "dictionary/edit";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Dictionary dictionary) {
        try {
            return new Result<Integer>().ok().setData(dictionaryService.insert(dictionary));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, DictionaryExceptionCode.DICTIONARY_ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Dictionary dictionary) {
        try {
            return new Result<Integer>().ok().setData(dictionaryService.update(dictionary));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, DictionaryExceptionCode.DICTIONARY_EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/get")
    @ResponseBody
    public Result<Dictionary> get(Dictionary dictionary) {
        try {
            return new Result<Dictionary>().ok().setData(dictionaryService.get(dictionary.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/list")
    @ResponseBody
    public Result<List<Dictionary>> list(Dictionary dictionary) {
        try {
            dictionary.asOrderBy("SORTS", OrderByObj.ASC);
            return new Result<List<Dictionary>>().ok().setData(dictionaryService.list(dictionary));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Dictionary dictionary) {
        try {
            return new Result<Integer>().ok().setData(dictionaryService.delete(dictionary.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
