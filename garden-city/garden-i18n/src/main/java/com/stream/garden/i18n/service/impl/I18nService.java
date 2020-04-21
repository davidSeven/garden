package com.stream.garden.i18n.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.i18n.dao.II18nDao;
import com.stream.garden.i18n.exception.I18nExceptionCode;
import com.stream.garden.i18n.model.I18n;
import com.stream.garden.i18n.service.II18nService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-10-24 17:17
 */
@Service
public class I18nService extends AbstractBaseService<I18n, II18nDao> implements II18nService {

    @Override
    public int insert(I18n i18n) throws ApplicationException {
        I18n paramI18n = new I18n();
        paramI18n.setCode(i18n.getCode());
        paramI18n.setLanguageType(i18n.getLanguageType());
        if (super.exists(paramI18n)) {
            throw new ApplicationException(I18nExceptionCode.I18N_CODE_REPEAT, i18n.getCode());
        }
        return super.insertSelective(i18n);
    }

    @Override
    public int update(I18n i18n) throws ApplicationException {
        I18n paramI18n = new I18n();
        paramI18n.setId(i18n.getId());
        paramI18n.setCode(i18n.getCode());
        paramI18n.setLanguageType(i18n.getLanguageType());
        if (super.exists(paramI18n)) {
            throw new ApplicationException(I18nExceptionCode.I18N_CODE_REPEAT, i18n.getCode());
        }
        return super.updateSelective(i18n);
    }
}
