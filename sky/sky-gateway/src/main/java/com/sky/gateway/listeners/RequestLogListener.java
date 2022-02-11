package com.sky.gateway.listeners;

import com.sky.gateway.events.RequestLogEvent;
import com.sky.system.api.model.RequestLog;
import com.sky.system.client.service.RequestLogClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RequestLogListener {

    @Autowired
    private RequestLogClientService requestLogClientService;

    @Async
    @EventListener
    public void onApplicationEvent(RequestLogEvent event) {
        this.requestLogClientService.save((RequestLog) event.getSource());
    }
}
