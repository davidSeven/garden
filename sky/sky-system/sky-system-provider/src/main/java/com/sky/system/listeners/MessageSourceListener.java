package com.sky.system.listeners;

import com.sky.system.config.I18nMessageSource;
import com.sky.system.events.MessageSourceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceListener {

    @Autowired
    private MessageSource messageSource;

    @Async
    @EventListener
    public void onApplicationEvent(MessageSourceEvent event) {
        Object source = event.getSource();
        String code = (String) source;
        ((I18nMessageSource) messageSource).refresh(code);
    }
}
