package com.sky.gateway.config;

import com.sky.gateway.decorator.PayloadServerWebExchangeDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.WebFilter;

@Configuration
public class GatewayConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter webFilter() {
        return (exchange, chain) -> chain.filter(new PayloadServerWebExchangeDecorator(exchange));
    }
}
