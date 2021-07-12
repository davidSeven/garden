package com.sky.gateway.filter;

import com.sky.system.api.dto.AccessDto;
import com.sky.system.api.dto.UserLoginDto;
import com.sky.system.api.dto.VerificationDto;
import com.sky.system.client.service.AuthenticationClientService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @date 2020-12-15 015 11:56
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private AuthenticationClientService authenticationClientService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 1. 获取token
        String authorization = request.getHeaders().getFirst("Authorization");
        String ip = request.getHeaders().getFirst("Gateway-X-Access-IP");

        logger.info("当前请求的url:{}, method:{}, token:{}, ip:{}", request.getURI().getPath(), request.getMethodValue(), authorization, ip);

        ServerWebExchange build = null;
        if (Strings.isNotEmpty(authorization)) {
            VerificationDto verificationDto = new VerificationDto();
            verificationDto.setAuthorization(authorization);
            verificationDto.setIp(ip);
            UserLoginDto userLoginDto = this.authenticationClientService.verification(verificationDto);
            if (null != userLoginDto) {
                try {
                    ServerHttpRequest host = exchange.getRequest().mutate()
                            .header("X-Access-Id", String.valueOf(userLoginDto.getId()))
                            .header("X-Access-Code", userLoginDto.getCode())
                            // 中文字符需要编码
                            .header("X-Access-Name", URLEncoder.encode(userLoginDto.getName(), "utf-8"))
                            .build();
                    build = exchange.mutate().request(host).build();
                } catch (UnsupportedEncodingException e) {
                    build = exchange;
                }
            }
            // access
            AccessDto accessDto = new AccessDto();
            accessDto.setAuthorization(authorization);
            this.authenticationClientService.access(accessDto);
        }
        if (null == build) {
            build = exchange;
        }
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
