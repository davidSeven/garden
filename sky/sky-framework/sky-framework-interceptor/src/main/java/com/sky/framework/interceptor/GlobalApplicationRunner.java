package com.sky.framework.interceptor;

import com.sky.framework.interceptor.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @date 2020-09-23 023 9:49
 */
@Component
public class GlobalApplicationRunner implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {

        runCompleted();
    }

    public void runCompleted() {
        Environment environment = ApplicationUtil.getApplicationContext().getEnvironment();
        String name = environment.getProperty("spring.application.name");
        String port = environment.getProperty("server.port");
        String active = environment.getProperty("spring.profiles.active");
        String contextPath = environment.getProperty("server.servlet.context-path");
        logger.info("--------------------------------------------");
        logger.info("---项目( {} )启动完成，端口：{}，环境：{}，路径：{}---", name, port, active, contextPath);
        logger.info("--------------------------------------------");
    }
}
