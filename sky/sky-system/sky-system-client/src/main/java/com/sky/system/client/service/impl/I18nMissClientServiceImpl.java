package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.I18nMiss;
import com.sky.system.client.feign.I18nMissClientFeign;
import com.sky.system.client.service.I18nMissClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class I18nMissClientServiceImpl implements I18nMissClientService {

    @Autowired
    private I18nMissClientFeign i18nMissClientFeign;

    @Override
    public boolean save(I18nMiss i18nMiss) {
        return ResponseDto.getDataBAndException(this.i18nMissClientFeign.save(i18nMiss));
    }
}
