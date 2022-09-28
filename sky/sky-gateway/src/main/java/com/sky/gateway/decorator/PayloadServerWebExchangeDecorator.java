package com.sky.gateway.decorator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

public class PayloadServerWebExchangeDecorator extends ServerWebExchangeDecorator {

    private final DecoratorContext decoratorContext;
    private final PartnerServerHttpRequestDecorator requestDecorator;
    private final PartnerServerHttpResponseDecorator responseDecorator;

    public PayloadServerWebExchangeDecorator(ServerWebExchange delegate) {
        super(delegate);
        decoratorContext = new DecoratorContext();
        requestDecorator = new PartnerServerHttpRequestDecorator(delegate.getRequest(), decoratorContext);
        responseDecorator = new PartnerServerHttpResponseDecorator(delegate.getResponse(), decoratorContext);
    }

    @NonNull
    @Override
    public ServerHttpRequest getRequest() {
        return requestDecorator;
    }

    @NonNull
    @Override
    public ServerHttpResponse getResponse() {
        return responseDecorator;
    }

    public DecoratorContext getDecoratorContext() {
        return decoratorContext;
    }
}
