package com.sky.system.listeners;

import com.sky.system.api.model.I18nMiss;
import com.sky.system.events.I18nMissEvent;
import com.sky.system.service.I18nMissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class I18nMissListener {

    @Autowired
    private I18nMissService i18nMissService;

    @Async
    @EventListener
    public void onApplicationEvent(I18nMissEvent event) {
        Object source = event.getSource();
        String code = (String) source;
        String languageType = event.getLanguageType();
        I18nMiss i18nMiss = new I18nMiss();
        i18nMiss.setCode(code);
        i18nMiss.setLanguageType(languageType);
        this.i18nMissService.save(i18nMiss);
    }
}
