package com.stream.garden.system.function.service.impl;

import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.function.dao.IFunctionDao;
import com.stream.garden.system.function.model.Function;
import com.stream.garden.system.function.service.IFunctionService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-07-20 15:46
 */
@Service
public class FunctionServiceImpl extends AbstractBaseService<Function, String> implements IFunctionService {

    public FunctionServiceImpl(IFunctionDao iFunctionDao) {
        super(iFunctionDao);
    }
}
