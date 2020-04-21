package com.stream.garden.lookup.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.lookup.dao.ILookupDao;
import com.stream.garden.lookup.exception.LookupExceptionCode;
import com.stream.garden.lookup.model.Lookup;
import com.stream.garden.lookup.service.ILookupService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 */
@Service
public class LookupService extends AbstractBaseService<Lookup, String, ILookupDao> implements ILookupService {

    @Override
    public int insert(Lookup lookup) throws ApplicationException {
        Lookup paramLookup = new Lookup();
        paramLookup.setCode(lookup.getCode());
        if (super.exists(paramLookup)) {
            throw new ApplicationException(LookupExceptionCode.LOOKUP_ADD_REPEAT, lookup.getCode());
        }
        return super.insertSelective(lookup);
    }

    @Override
    public int update(Lookup lookup) throws ApplicationException {
        Lookup paramLookup = new Lookup();
        paramLookup.setId(lookup.getId());
        paramLookup.setCode(lookup.getCode());
        if (super.exists(paramLookup)) {
            throw new ApplicationException(LookupExceptionCode.LOOKUP_ADD_REPEAT, lookup.getCode());
        }
        return super.updateSelective(lookup);
    }
}
