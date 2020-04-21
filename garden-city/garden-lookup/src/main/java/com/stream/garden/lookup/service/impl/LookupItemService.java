package com.stream.garden.lookup.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.lookup.dao.ILookupItemDao;
import com.stream.garden.lookup.exception.LookupExceptionCode;
import com.stream.garden.lookup.model.LookupItem;
import com.stream.garden.lookup.service.ILookupItemService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 */
@Service
public class LookupItemService extends AbstractBaseService<LookupItem, String, ILookupItemDao> implements ILookupItemService {

    @Override
    public int insert(LookupItem lookupItem) throws ApplicationException {
        LookupItem paramLookupItem = new LookupItem();
        paramLookupItem.setCode(lookupItem.getCode());
        if (super.exists(paramLookupItem)) {
            throw new ApplicationException(LookupExceptionCode.LOOKUP_ITEM_ADD_REPEAT, lookupItem.getCode());
        }
        return super.insertSelective(lookupItem);
    }

    @Override
    public int update(LookupItem lookupItem) throws ApplicationException {
        LookupItem paramLookupItem = new LookupItem();
        paramLookupItem.setId(lookupItem.getId());
        paramLookupItem.setCode(lookupItem.getCode());
        if (super.exists(paramLookupItem)) {
            throw new ApplicationException(LookupExceptionCode.LOOKUP_ITEM_ADD_REPEAT, lookupItem.getCode());
        }
        return super.updateSelective(lookupItem);
    }
}
