package com.sky.gateway.events;

import com.sky.gateway.util.ApplicationUtil;
import org.springframework.context.ApplicationEvent;

public class AccessEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccessEvent(Object source) {
        super(source);
    }

    public static void publishEvent(String authorization) {
        ApplicationUtil.getApplicationContext().publishEvent(new AccessEvent(authorization));
    }
}
