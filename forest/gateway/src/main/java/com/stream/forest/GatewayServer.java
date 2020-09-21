package com.stream.forest;

import com.stream.forest.util.ProjectUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * GatewayServer
 */
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration.class,
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@EnableEurekaClient
public class GatewayServer {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        ApplicationContext applicationContext = SpringApplication.run(GatewayServer.class, args);
        ProjectUtil.afterRun(applicationContext);
    }

}
