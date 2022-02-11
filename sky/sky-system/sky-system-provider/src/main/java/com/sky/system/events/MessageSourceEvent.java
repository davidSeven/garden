package com.sky.system.events;

import com.sky.framework.interceptor.util.ApplicationUtil;
import org.springframework.context.ApplicationEvent;

public class MessageSourceEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MessageSourceEvent(Object source) {
        super(source);
    }

    public static void publishEvent(String code) {
        ApplicationUtil.getApplicationContext().publishEvent(new MessageSourceEvent(code));
    }
}
