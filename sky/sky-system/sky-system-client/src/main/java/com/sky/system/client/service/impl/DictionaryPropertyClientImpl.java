package com.sky.system.client.service.impl;

import com.sky.system.api.service.DictionaryProperty;
import com.sky.system.client.service.DictionaryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryPropertyClientImpl implements DictionaryProperty {

    @Autowired
    private DictionaryClientService dictionaryClientService;

    @Override
    public String getValue(String code) {
        return this.dictionaryClientService.getValue(code);
    }
}
