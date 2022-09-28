package com.sky.gateway.decorator;

import com.sky.gateway.events.RequestLogEvent;
import com.sky.system.api.model.RequestLog;
import org.springframework.core.io.buffer.DataBuffer;

import java.util.Date;

public class ResponseParamsHandle {

    @SuppressWarnings({"unchecked"})
    public static <T extends DataBuffer> T chain(DecoratorContext decoratorContext, T buffer) {
        ParamsUtils.BodyDecorator bodyDecorator = ParamsUtils.buildBodyDecorator(buffer);
        // 请求日志
        RequestLog requestLog = new RequestLog();
        requestLog.setRequestDomain(decoratorContext.getScheme());
        requestLog.setRequestUri(decoratorContext.getUri());
        requestLog.setRequestMethod(decoratorContext.getMethod());
        requestLog.setRequestIp(decoratorContext.getIp());
        requestLog.setRequestHeader(decoratorContext.getRequestHeader());
        requestLog.setRequestBody(decoratorContext.getRequestBody());
        if (null != decoratorContext.getRequestBody()) {
            requestLog.setRequestBodySize((long) decoratorContext.getRequestBody().length());
        }
        requestLog.setRequestTime(decoratorContext.getRequestTime());
        requestLog.setResponseHeader(decoratorContext.getResponseHeader());
        requestLog.setResponseBody(bodyDecorator.getBody());
        if (null != requestLog.getResponseBody()) {
            requestLog.setResponseBodySize((long) requestLog.getResponseBody().length());
        }
        requestLog.setResponseTime(new Date());
        requestLog.setResponseStatus(decoratorContext.getResponseStatus());
        if (null != requestLog.getResponseTime() && null != requestLog.getRequestTime()) {
            requestLog.setRequestConsumeTime(requestLog.getResponseTime().getTime() - requestLog.getRequestTime().getTime());
        } else {
            requestLog.setRequestConsumeTime(0L);
        }
        RequestLogEvent.publishEvent(requestLog);
        return (T) bodyDecorator.getDataBuffer();
    }

}
