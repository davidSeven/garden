package com.stream.garden.lookup.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.lookup.dao.ILookupItemDao;
import com.stream.garden.lookup.model.LookupItem;
import com.stream.garden.lookup.service.ILookupItemService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 */
@Service
public class LookupItemService extends AbstractBaseService<LookupItem, String> implements ILookupItemService {
    public LookupItemService(ILookupItemDao iLookupItemDao) {
        super(iLookupItemDao);
    }
}
