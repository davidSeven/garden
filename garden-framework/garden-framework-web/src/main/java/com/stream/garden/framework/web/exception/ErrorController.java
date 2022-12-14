package com.stream.garden.framework.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author garden
 * @date 2019-06-24 10:25
 */
@Controller
public class ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/error/404")
    public String error404() {
        logger.error("error 404");
        return "error/404";
    }

    @RequestMapping(value = "/error/500")
    public String error500() {
        logger.error("error 500");
        return "error/500";
    }
}
