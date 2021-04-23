package com.sky.system.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sky
 * @date 2021-04-23 14:01
 */
@Api(tags = "日志管理")
@RestController
@RequestMapping(value = "/log")
public class LogController {
    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping(value = "/level/{logLevel}")
    public String changeLogLevel(@PathVariable("logLevel") String logLevel) {
        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.getLogger("com.sky").setLevel(Level.valueOf(logLevel));
        } catch (Exception e) {
            logger.error("动态修改日志级别出错", e);
            return "fail";
        }
        return "success";
    }

    @GetMapping("/print/log")
    public String printLog() {
        StringBuilder builder = new StringBuilder();
        // TRACE < DEBUG < INFO < WARN < ERROR < FATAL
        if (logger.isTraceEnabled()) {
            builder.append("trace /r/n");
        }
        if (logger.isDebugEnabled()) {
            builder.append("debug /r/n");
        }
        if (logger.isInfoEnabled()) {
            builder.append("info /r/n");
        }
        if (logger.isWarnEnabled()) {
            builder.append("warn /r/n");
        }
        if (logger.isErrorEnabled()) {
            builder.append("error /r/n");
        }
        return builder.toString();
    }
}
