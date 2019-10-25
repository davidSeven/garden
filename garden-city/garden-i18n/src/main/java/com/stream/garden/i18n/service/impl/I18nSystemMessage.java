package com.stream.garden.i18n.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.interfaces.ISystemMessage;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.i18n.constant.I18nConstant;
import com.stream.garden.i18n.model.I18n;
import com.stream.garden.i18n.service.II18nService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author garden
 * @date 2019-10-25 11:37
 */
@Service
public class I18nSystemMessage implements ISystemMessage {
    private Logger logger = LoggerFactory.getLogger(I18nSystemMessage.class);

    @Autowired
    private II18nService i18nService;

    @Override
    public Map<String, String> getMessage(String languageType) {
        Map<String, String> i18nMap = null;
        try {
            I18n paramI18n = new I18n();
            paramI18n.setLanguageType(languageType);
            paramI18n.setState(I18nConstant.STATE_1);
            List<I18n> list = this.i18nService.list(paramI18n);
            if (CollectionUtil.isNotEmpty(list)) {
                i18nMap = new HashMap<>(list.size());
                for (I18n i18n : list) {
                    i18nMap.put(i18n.getCode(), i18n.getValue());
                }
            }
        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
        }
        return i18nMap;
    }
}
