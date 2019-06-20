package com.stream.garden.framework.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author garden
 * @date 2019-06-19 17:33
 */
@Configuration
public class GlobalWebMvcConfigurer extends GlobalConfig implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GlobalWebMvcConfigurer() {
        logger.debug("GlobalWebMvcConfigurer构造方法执行");
        if (null != jwt) {
            logger.debug("配置了jwt，name：{}", jwt.getName());
        } else {
            logger.debug("没有配置jwt");
        }
    }

}
