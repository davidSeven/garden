package com.sky.system.api.service;

import com.sky.system.api.model.I18n;

import java.util.List;
import java.util.Locale;

public interface I18nProperty {

    List<Locale> getLocaleList();

    I18n get(String code, Locale locale);

    List<I18n> list(String code, Locale locale);

    List<I18n> list(List<String> prefixList, Locale locale, int size);

    void miss(String code, Locale locale);
}
