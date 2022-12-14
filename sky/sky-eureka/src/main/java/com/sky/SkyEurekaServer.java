package com.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author city
 */
@SpringBootApplication
@EnableEurekaServer
public class SkyEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(SkyEurekaServer.class, args);
    }
}
