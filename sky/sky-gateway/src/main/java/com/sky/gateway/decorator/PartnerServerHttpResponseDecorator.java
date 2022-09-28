package com.sky.gateway.decorator;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;

@Slf4j
public class PartnerServerHttpResponseDecorator extends ServerHttpResponseDecorator {

    private final DecoratorContext decoratorContext;

    PartnerServerHttpResponseDecorator(ServerHttpResponse delegate, DecoratorContext decoratorContext) {
        super(delegate);
        this.decoratorContext = decoratorContext;
    }

    @NonNull
    @Override
    public Mono<Void> writeAndFlushWith(@NonNull Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return super.writeAndFlushWith(body);
    }

    @SuppressWarnings({"unchecked"})
    @NonNull
    @Override
    public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        final MediaType contentType = super.getHeaders().getContentType();
        if (ParamsUtils.CHAIN_MEDIA_TYPE.contains(contentType)) {
            decoratorContext.setResponseHeader(JSON.toJSONString(getHeaders()));
            Integer rawStatusCode = getRawStatusCode();
            if (null == rawStatusCode) {
                rawStatusCode = 0;
            }
            decoratorContext.setResponseStatus(rawStatusCode);
            if (body instanceof Mono) {
                final Mono<DataBuffer> monoBody = (Mono<DataBuffer>) body;
                return super.writeWith(monoBody.publishOn(single()).map(dataBuffer -> ResponseParamsHandle.chain(decoratorContext, dataBuffer)));
            } else if (body instanceof Flux) {
                Mono<DataBuffer> mono = DataBufferUtils.join(body);
                final Flux<DataBuffer> monoBody = mono.publishOn(single()).map(dataBuffer -> ResponseParamsHandle.chain(decoratorContext, dataBuffer)).flux();
                return super.writeWith(monoBody);
            }
        }
        return super.writeWith(body);
    }

}
