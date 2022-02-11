package com.sky.gateway.filter;

import com.sky.gateway.util.IpUtil;
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

/**
 * @date 2020-12-15 015 12:12
 */
@Component
public class RequestLogFilter implements GlobalFilter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String ipAddress = IpUtil.getIpAddress(request);
        long startTime = System.currentTimeMillis();
        // logger.info("当前请求的ip:{}", ipAddress);
        ServerHttpRequest host = exchange.getRequest().mutate()
                .header("Gateway-X-Access-IP", ipAddress)
                .build();
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build).then(Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis();
                long times = endTime - startTime;
                String uri = request.getURI().getPath();
                String method = request.getMethodValue();
                // nginx配置的ip
                HttpHeaders httpHeaders = request.getHeaders();
                String ip = httpHeaders.getFirst("Gateway-X-Access-IP");
                logger.info(">>>请求路径：{}[{}]，IP：{}，开始时间：{}，结束时间：{}，总耗时：{}", uri, method, ip, startTime, endTime, times);
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
