package com.sky.gateway.events;

import com.sky.gateway.util.ApplicationUtil;
import com.sky.system.api.model.RequestLog;
import org.springframework.context.ApplicationEvent;

public class RequestLogEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RequestLogEvent(Object source) {
        super(source);
    }

    public static void publishEvent(RequestLog requestLog) {
        ApplicationUtil.getApplicationContext().publishEvent(new RequestLogEvent(requestLog));
    }
}
