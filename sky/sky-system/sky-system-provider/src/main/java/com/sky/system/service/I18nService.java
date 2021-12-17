package com.sky.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;

import java.util.List;
import java.util.Locale;

public interface I18nService extends IService<I18n> {

    boolean create(I18nDto dto);

    boolean update(I18nDto dto);

    IPage<I18n> page(I18nQueryDto dto);

    List<I18n> list(I18nDto dto);

    List<Locale> getLocaleList();

    I18n get(String code, Locale locale);

    List<I18n> getList(String code);
}
