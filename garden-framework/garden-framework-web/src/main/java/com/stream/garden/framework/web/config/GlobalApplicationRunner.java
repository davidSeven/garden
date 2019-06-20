package com.stream.garden.framework.web.config;

import com.stream.garden.framework.web.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author garden
 */
@Component
public class GlobalApplicationRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {

        line();
        runCompleted();
        printServiceInfo();
        line();
    }

    public void line() {
        logger.info("--------------------------------------------");
    }

    public void runCompleted() {
        String name = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("spring.application.name");
        logger.info("--- 项目({})启动完成 ---", name);
    }

    public void printServiceInfo() {
        String active = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("spring.profiles.active");
        String contextPath = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path");
        String port = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("server.port");
        logger.info("--- active：{}，contextPath：{}，port：{} ---", active, contextPath, port);
    }
}
