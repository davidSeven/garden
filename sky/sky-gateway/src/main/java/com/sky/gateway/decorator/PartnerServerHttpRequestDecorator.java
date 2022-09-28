package com.sky.gateway.decorator;

import com.alibaba.fastjson.JSON;
import com.sky.gateway.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.scheduler.Schedulers.single;

@Slf4j
public class PartnerServerHttpRequestDecorator extends ServerHttpRequestDecorator {

    private final Flux<DataBuffer> body;

    public PartnerServerHttpRequestDecorator(ServerHttpRequest delegate, DecoratorContext decoratorContext) {
        super(delegate);
        Flux<DataBuffer> flux = super.getBody();
        MediaType contentType = delegate.getHeaders().getContentType();
        List<MediaType> acceptList = delegate.getHeaders().getAccept();
        if (ParamsUtils.CHAIN_MEDIA_TYPE.contains(contentType) || contains(acceptList)) {
            Mono<DataBuffer> mono = DataBufferUtils.join(flux);
            // 请求参数收集
            decoratorContext.setScheme(delegate.getURI().getScheme());
            decoratorContext.setUri(delegate.getPath().toString());
            decoratorContext.setMethod(delegate.getMethodValue());
            String ipAddress = IpUtil.getIpAddress(delegate);
            decoratorContext.setIp(ipAddress);
            decoratorContext.setRequestHeader(JSON.toJSONString(delegate.getHeaders()));
            decoratorContext.setId(delegate.getId());
            decoratorContext.setRequestBody("");
            body = mono.publishOn(single()).map(dataBuffer -> RequestParamsHandle.chain(delegate, dataBuffer, decoratorContext)).flux();
        } else {
            body = flux;
        }
    }

    @NonNull
    @Override
    public Flux<DataBuffer> getBody() {
        return body;
    }

    private boolean contains(List<MediaType> acceptList) {
        if (CollectionUtils.isEmpty(acceptList)) {
            return false;
        }
        for (MediaType mediaType : acceptList) {
            if (ParamsUtils.CHAIN_MEDIA_TYPE.contains(mediaType)) {
                return true;
            }
        }
        return false;
    }
}
