package com.sky.gateway.decorator;

import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ParamsUtils {

    public static final List<MediaType> CHAIN_MEDIA_TYPE = Arrays.asList(MediaType.TEXT_XML,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_JSON_UTF8,
            MediaType.TEXT_PLAIN,
            MediaType.TEXT_XML,
            MediaType.APPLICATION_FORM_URLENCODED
    );

    public static <T extends DataBuffer> BodyDecorator buildBodyDecorator(T buffer) {
        try {
            InputStream dataBuffer = buffer.asInputStream();
            byte[] bytes = IOUtils.toByteArray(dataBuffer);
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            DataBufferUtils.release(buffer);
            return new BodyDecorator(new String(bytes), nettyDataBufferFactory.wrap(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Data
    static class BodyDecorator {

        String body;

        DataBuffer dataBuffer;

        public BodyDecorator(String body, DataBuffer dataBuffer) {
            this.body = body;
            this.dataBuffer = dataBuffer;
        }

    }

}
