package com.stream.garden.system.role.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.function.vo.FunctionFieldTypeResultVO;
import com.stream.garden.system.role.model.RoleFunctionField;

import java.util.List;
import java.util.Map;

/**
 * @author garden
 * @date 2019/7/22 22:28
 */
public interface IRoleFunctionFieldService extends IBaseService<RoleFunctionField> {

    Map<Integer, List<FunctionFieldTypeResultVO>> listConfig(RoleFunctionField params) throws ApplicationException;

    void saveBatch(String roleId, List<RoleFunctionField> list) throws ApplicationException;
}
