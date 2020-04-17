package com.stream.garden.system.function.service.impl;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.function.dao.IFunctionFieldTypeDao;
import com.stream.garden.system.function.model.FunctionFieldType;
import com.stream.garden.system.function.service.IFunctionFieldTypeService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2020-04-08 19:14
 */
@Service
public class FunctionFieldTypeServiceImpl extends AbstractBaseService<FunctionFieldType, String> implements IFunctionFieldTypeService {

    public FunctionFieldTypeServiceImpl(IFunctionFieldTypeDao iFunctionFieldTypeDao) {
        super(iFunctionFieldTypeDao);
    }
}
