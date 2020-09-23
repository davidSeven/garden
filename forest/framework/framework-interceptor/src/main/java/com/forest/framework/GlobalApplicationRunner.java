package com.forest.framework;

import com.forest.framework.utils.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
        String name = ApplicationUtil.getApplicationContext().getEnvironment().getProperty("spring.application.name");
        logger.info("--------------------------------------------");
        logger.info("---项目(" + name + ")启动完成---");
        logger.info("--------------------------------------------");
    }
}
