package com.sky.system.client.service;

import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.model.I18n;

import java.util.List;
import java.util.Locale;

public interface I18nClientService {

    List<Locale> getLocaleList();

    I18n get(I18nDto dto);

    List<I18n> list(I18nDto dto);
}
