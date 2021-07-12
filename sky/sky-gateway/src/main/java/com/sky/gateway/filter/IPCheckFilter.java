package com.sky.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @date 2020-12-15 015 12:12
 */
@Component
public class IPCheckFilter implements GlobalFilter, Ordered {
    private static final String UNKNOWN = "unknown";
    private final Logger logger = LoggerFactory.getLogger(IPCheckFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String ipAddress = getIpAddress(request);
        logger.info("当前请求的ip:{}", ipAddress);
        ServerHttpRequest host = exchange.getRequest().mutate()
                .header("Gateway-X-Access-IP", ipAddress)
                .build();
        ServerWebExchange build = exchange.mutate().request(host).build();

        return chain.filter(build);
    }

    /**
     * 获取IP地址
     *
     * @param request request
     * @return ip
     */
    private String getIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        logger.debug("--- get ip address");
        String ip = headers.getFirst("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            if (null != remoteAddress) {
                InetAddress inetAddress = remoteAddress.getAddress();
                if (null != inetAddress) {
                    ip = inetAddress.getHostAddress();
                    ip = getLocalHost(ip);
                }
            }
        }
        logger.debug("--- ip address : [{}]", ip);
        return ip;
    }

    /**
     * 获取到本机的ip
     *
     * @param ip ip
     * @return ip
     */
    private String getLocalHost(String ip) {
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
            //根据网卡取本机配置的IP
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                logger.error(e.getMessage(), e);
            }
            if (null != inetAddress) {
                ip = inetAddress.getHostAddress();
            }
        }
        return ip;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
