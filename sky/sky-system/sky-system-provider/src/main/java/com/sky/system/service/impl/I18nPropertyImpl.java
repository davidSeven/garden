package com.sky.system.service.impl;

import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.model.I18n;
import com.sky.system.api.model.I18nMiss;
import com.sky.system.api.service.I18nMissProperty;
import com.sky.system.api.service.I18nProperty;
import com.sky.system.events.I18nMissEvent;
import com.sky.system.service.I18nMissService;
import com.sky.system.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class I18nPropertyImpl implements I18nProperty, I18nMissProperty {

    @Autowired
    private I18nService i18nService;
    @Autowired
    private I18nMissService i18nMissService;

    @Override
    public List<Locale> getLocaleList() {
        return this.i18nService.getLocaleList();
    }

    @Override
    public I18n get(String code, Locale locale) {
        return this.i18nService.get(code, locale);
    }

    @Override
    public List<I18n> list(String code, Locale locale) {
        I18nDto i18nDto = new I18nDto();
        i18nDto.setCode(code);
        if (null != locale) {
            i18nDto.setLanguageType(locale.toString());
        }
        return this.i18nService.list(i18nDto);
    }

    @Override
    public List<I18n> list(List<String> prefixList, Locale locale, int size) {
        I18nDto i18nDto = new I18nDto();
        i18nDto.setPrefixList(prefixList);
        if (null != locale) {
            i18nDto.setLanguageType(locale.toString());
        }
        i18nDto.setSize(size);
        return this.i18nService.list(i18nDto);
    }

    @Override
    public void miss(String code, Locale locale) {
        I18nMissEvent.publishEvent(code, locale.toString());
    }

    @Override
    public void save(I18nMiss i18nMiss) {
        this.i18nMissService.save(i18nMiss);
    }
}
