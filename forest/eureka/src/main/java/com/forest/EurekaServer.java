package com.forest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

/**
 *
 */
@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaServer {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        ApplicationContext applicationContext = SpringApplication.run(EurekaServer.class, args);
        afterRun(applicationContext);
    }

    private static String getIp(Environment env) throws UnknownHostException {
        Assert.notNull(env, "参数错误");
        String ip = env.getProperty("eureka.instance.ip-address");
        if (StringUtils.isEmpty(ip)) {
            ip = env.getProperty("spring.cloud.client.ip-address");
        }
        InetAddress address = InetAddress.getLocalHost();
        if (StringUtils.isEmpty(ip)) {
            ip = address.getHostAddress();
        }
        Assert.notNull(ip, "获取本机IP失败");
        return ip;
    }

    private static String getPort(Environment env) {
        Assert.notNull(env, "参数错误");
        String port = env.getProperty("server.port");
        Assert.notNull(port, "配置server.port不存在");
        return port;
    }

    private static void afterRun(ApplicationContext applicationContext) throws SocketException, UnknownHostException {
        String spit = "------";
        // 打印HttpMessageConverter
        log.info(spit);
        List<HttpMessageConverter<?>> list = applicationContext.getBean(HttpMessageConverters.class).getConverters();
        for (HttpMessageConverter<?> hmc : list) {
            log.info(hmc.getClass().toString());
        }
        // 打印所有网卡
        log.info(spit);
        for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements(); ) {
            NetworkInterface ifc = nis.nextElement();
            log.info(ifc.getIndex() + "," + ifc.getName() + "," + ifc.getDisplayName() + "," + ifc.getInterfaceAddresses());
        }
        // 打印本应用的ip和端口，Bean的数量
        log.info(spit);
        Environment env = applicationContext.getEnvironment();
        String app = env.getProperty("spring.application.name");
        String ip = getIp(env);
        String port = getPort(env);
        log.info("app:" + app + ",ip:" + ip + ",port:" + port + ",beans:" + applicationContext.getBeanDefinitionCount());
        // 启动成功
        System.out.println("Congratulations!");
    }

}
