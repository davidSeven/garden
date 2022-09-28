package com.sky.system.service.impl;

import com.sky.system.api.service.DictionaryProperty;
import com.sky.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DictionaryPropertyImpl implements DictionaryProperty {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public String getValue(String code) {
        return this.dictionaryService.getValue(code);
    }
}
