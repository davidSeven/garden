package com.stream.garden.framework.web.config;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.framework.web.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author garden
 */
@Component
public class GlobalApplicationRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        line();
        logger.debug(">>>path:{}", globalConfig.getPath());
        logger.debug(">>>path:{}", globalConfig.path);
        logger.debug(">>>path:{}", GlobalConfig.path);
        logger.debug(">>>uploadPath:{}", GlobalConfig.uploadPath);
        logger.debug(">>>UPLOAD_DIR:{}", GlobalConfig.UPLOAD_DIR);
        printController();
        runCompleted();
        printServiceInfo();
        line();
    }

    private void printController() {
        ApplicationContext applicationContext = ApplicationUtil.getApplicationContext();
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);
        if (null != beans) {
            beans.forEach((key, value) -> {
                logger.debug("--- {}", key);
                // logger.debug("--- {}", value);
                RequestMapping requestMapping = value.getClass().getAnnotation(RequestMapping.class);
                if (null != requestMapping) {
                    String[] objects = requestMapping.value();
                    logger.debug("--- {}", JSONObject.toJSONString(objects));
                }
            });
        }

        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        if (null != handlerMapping) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            logger.debug("--- {}");
            handlerMethods.forEach((key, value) -> {
                logger.debug("--- {}", key.toString());
                logger.debug("--- {}", value.getMethod().getName());
            });
        }
    }

    public void line() {
        logger.info("--------------------------------------------");
    }

    private void runCompleted() {
        String name = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("spring.application.name");
        logger.info("--- 项目({})启动完成 ---", name);
    }

    private void printServiceInfo() {
        String active = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("spring.profiles.active");
        String contextPath = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path");
        String port = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("server.port");
        logger.info("--- active：{}，contextPath：{}，port：{} ---", active, contextPath, port);
    }
}
