package com.stream.garden.i18n.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.i18n.dao.II18nDao;
import com.stream.garden.i18n.model.I18n;
import com.stream.garden.i18n.service.II18nService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-10-24 17:17
 */
@Service
public class I18nService extends AbstractBaseService<I18n, String> implements II18nService {

    public I18nService(II18nDao ii18nDao) {
        super(ii18nDao);
    }
}
