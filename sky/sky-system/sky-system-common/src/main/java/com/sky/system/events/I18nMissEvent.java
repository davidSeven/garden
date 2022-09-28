package com.sky.system.events;

import com.sky.framework.utils.ApplicationUtil;
import org.springframework.context.ApplicationEvent;

public class I18nMissEvent extends ApplicationEvent {

    private String languageType;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public I18nMissEvent(Object source) {
        super(source);
    }

    public I18nMissEvent(Object source, String languageType) {
        super(source);
        this.languageType = languageType;
    }

    public static void publishEvent(String code, String languageType) {
        ApplicationUtil.getApplicationContext().publishEvent(new I18nMissEvent(code, languageType));
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }
}
