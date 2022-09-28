package com.sky.system.client.service.impl;

import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.model.I18n;
import com.sky.system.api.model.I18nMiss;
import com.sky.system.api.service.I18nMissProperty;
import com.sky.system.api.service.I18nProperty;
import com.sky.system.client.service.I18nClientService;
import com.sky.system.client.service.I18nMissClientService;
import com.sky.system.events.I18nMissEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class I18nPropertyClientImpl implements I18nProperty, I18nMissProperty {

    @Autowired
    private I18nClientService i18nClientService;
    @Autowired
    private I18nMissClientService i18nMissClientService;

    @Override
    public List<Locale> getLocaleList() {
        return this.i18nClientService.getLocaleList();
    }

    @Override
    public I18n get(String code, Locale locale) {
        I18nDto i18nDto = new I18nDto();
        i18nDto.setCode(code);
        if (null != locale) {
            i18nDto.setLanguageType(locale.toString());
        }
        return this.i18nClientService.get(i18nDto);
    }

    @Override
    public List<I18n> list(String code, Locale locale) {
        I18nDto i18nDto = new I18nDto();
        i18nDto.setCode(code);
        if (null != locale) {
            i18nDto.setLanguageType(locale.toString());
        }
        return this.i18nClientService.list(i18nDto);
    }

    @Override
    public List<I18n> list(List<String> prefixList, Locale locale, int size) {
        I18nDto i18nDto = new I18nDto();
        i18nDto.setPrefixList(prefixList);
        if (null != locale) {
            i18nDto.setLanguageType(locale.toString());
        }
        i18nDto.setSize(size);
        return this.i18nClientService.list(i18nDto);
    }

    @Override
    public void miss(String code, Locale locale) {
        I18nMissEvent.publishEvent(code, locale.toString());
    }

    @Override
    public void save(I18nMiss i18nMiss) {
        this.i18nMissClientService.save(i18nMiss);
    }
}
