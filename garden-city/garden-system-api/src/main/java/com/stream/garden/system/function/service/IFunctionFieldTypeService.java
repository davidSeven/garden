package com.stream.garden.system.function.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.function.model.FunctionFieldType;
import com.stream.garden.system.function.vo.FunctionFieldTypeResultVO;
import com.stream.garden.system.function.vo.FunctionFieldTypeSaveVO;
import com.stream.garden.system.function.vo.FunctionFieldTypeVO;

import java.util.List;

/**
 * @author garden
 * @date 2020-04-08 19:13
 */
public interface IFunctionFieldTypeService extends IBaseService<FunctionFieldType, String> {

    int save(FunctionFieldTypeSaveVO vo) throws ApplicationException;

    List<FunctionFieldTypeResultVO> listView(FunctionFieldType params) throws ApplicationException;
}
