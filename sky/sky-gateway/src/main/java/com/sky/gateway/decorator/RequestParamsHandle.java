package com.sky.gateway.decorator;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestParamsHandle {

    @SuppressWarnings({"unchecked"})
    public static <T extends DataBuffer> T chain(ServerHttpRequest delegate, T buffer, DecoratorContext decoratorContext) {
        ParamsUtils.BodyDecorator bodyDecorator = ParamsUtils.buildBodyDecorator(buffer);
        String body = JSON.toJSONString(validParams(getParams(delegate, bodyDecorator.getBody())));
        decoratorContext.setRequestBody(body);
        decoratorContext.setRequestTime(new Date());
        return (T) bodyDecorator.getDataBuffer();
    }

    public static Map<String, Object> getParams(ServerHttpRequest delegate, String body) {
        // 整理参数
        Map<String, Object> params = new HashMap<>();
        MultiValueMap<String, String> queryParams = delegate.getQueryParams();
        if (!queryParams.isEmpty()) {
            params.putAll(queryParams);
        }
        if (!StringUtils.isEmpty(body)) {
            params.putAll(JSON.parseObject(body));
        }
        return params;
    }

    public static Map<String, Object> validParams(Map<String, Object> params) {
        return params;
    }

}
