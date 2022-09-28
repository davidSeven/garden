package com.sku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhangyuyuan
 * @date 2021-07-22 16:24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SkyOAuthResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyOAuthResourceApplication.class, args);
    }
}
