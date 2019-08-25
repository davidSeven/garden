package com.stream.garden.system.function.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.function.model.Function;

import java.util.List;

/**
 * @author garden
 * @date 2019-07-20 15:45
 */
public interface IFunctionService extends IBaseService<Function, String> {


    List<Function> getRoleFunction() throws ApplicationException;
}
