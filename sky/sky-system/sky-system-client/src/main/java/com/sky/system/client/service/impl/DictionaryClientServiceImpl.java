package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.client.feign.DictionaryClientFeign;
import com.sky.system.client.service.DictionaryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryClientServiceImpl implements DictionaryClientService {

    @Autowired
    private DictionaryClientFeign dictionaryClientFeign;

    @Override
    public String getValue(String code) {
        return ResponseDto.getDataAndException(this.dictionaryClientFeign.getValue(code));
    }
}
