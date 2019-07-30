package com.stream.garden.lookup.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.lookup.dao.ILookupDao;
import com.stream.garden.lookup.model.Lookup;
import com.stream.garden.lookup.service.ILookupService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 */
@Service
public class LookupService extends AbstractBaseService<Lookup, String> implements ILookupService {
    public LookupService(ILookupDao iLookupDao) {
        super(iLookupDao);
    }
}
