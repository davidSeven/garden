package com.stream.garden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author garden
 * @date 2019-06-19 13:54
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
// @ServletComponentScan
//@NacosPropertySource(dataId = "garden-system-web", autoRefreshed = true)
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SystemApplication.class);
        springApplication.run(args);
    }
}
