package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.model.I18n;
import com.sky.system.client.feign.I18nClientFeign;
import com.sky.system.client.service.I18nClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class I18nClientServiceImpl implements I18nClientService {

    @Autowired
    private I18nClientFeign i18nClientFeign;

    @Override
    public List<Locale> getLocaleList() {
        return ResponseDto.getDataAndException(this.i18nClientFeign.getLocaleList());
    }

    @Override
    public I18n get(I18nDto dto) {
        return ResponseDto.getDataAndException(this.i18nClientFeign.get(dto));
    }

    @Override
    public List<I18n> list(I18nDto dto) {
        return ResponseDto.getDataAndException(this.i18nClientFeign.list(dto));
    }
}
