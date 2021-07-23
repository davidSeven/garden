package com.sku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhangyuyuan
 * @date 2021-07-22 16:24
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SkyOAuthApplication {

    // http://127.0.0.1:8080/oauth/authorize?client_id=client1&response_type=code&scope=scope1&redirect_uri=http://www.baidu.com
    public static void main(String[] args) {
        SpringApplication.run(SkyOAuthApplication.class, args);
    }
}
