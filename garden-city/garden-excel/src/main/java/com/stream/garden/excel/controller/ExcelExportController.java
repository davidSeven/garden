package com.stream.garden.excel.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author garden
 * @date 2019-11-04 19:55
 */
@Controller
@RequestMapping(value = "/excel/export")
public class ExcelExportController {
    private Logger logger = LoggerFactory.getLogger(ExcelExportController.class);

    /**
     * 导出数据
     *
     * @param request  request
     * @param response response
     */
    @GetMapping(value = "/exportData")
    public Result exportData(HttpServletRequest request, HttpServletResponse response) {
        try {

            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
