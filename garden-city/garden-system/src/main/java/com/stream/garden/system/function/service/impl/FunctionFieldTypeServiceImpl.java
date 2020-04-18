package com.stream.garden.system.function.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.function.dao.IFunctionFieldTypeDao;
import com.stream.garden.system.function.model.FunctionField;
import com.stream.garden.system.function.model.FunctionFieldType;
import com.stream.garden.system.function.service.IFunctionFieldService;
import com.stream.garden.system.function.service.IFunctionFieldTypeService;
import com.stream.garden.system.function.vo.FunctionFieldTypeResultVO;
import com.stream.garden.system.function.vo.FunctionFieldTypeSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author garden
 * @date 2020-04-08 19:14
 */
@Service
public class FunctionFieldTypeServiceImpl extends AbstractBaseService<FunctionFieldType, String> implements IFunctionFieldTypeService {

    @Autowired
    private IFunctionFieldService functionFieldService;

    public FunctionFieldTypeServiceImpl(IFunctionFieldTypeDao iFunctionFieldTypeDao) {
        super(iFunctionFieldTypeDao);
    }

    private IFunctionFieldTypeDao getDao() {
        return (IFunctionFieldTypeDao) this.baseMapper;
    }

    @Override
    public int save(FunctionFieldTypeSaveVO vo) throws ApplicationException {
        FunctionFieldType params = new FunctionFieldType();
        params.setFunctionId(vo.getFunctionId());
        params.setType(vo.getType());
        int delete = this.getDao().deleteByFunctionId(params);
        List<FunctionFieldType> list = vo.getList();
        if (CollectionUtil.isNotEmpty(list)) {
            for (FunctionFieldType functionFieldType : list) {
                functionFieldType.setFunctionId(vo.getFunctionId());
                functionFieldType.setType(vo.getType());
            }
            this.insertBatch(list);
        }
        return delete;
    }

    @Override
    public List<FunctionFieldTypeResultVO> listView(FunctionFieldType params) throws ApplicationException {
        // 查询字段
        FunctionField functionField = new FunctionField();
        String functionId = params.getFunctionId();
        functionField.setFunctionId(functionId);
        List<FunctionField> fieldList = this.functionFieldService.list(functionField);
        if (CollectionUtil.isNotEmpty(fieldList)) {
            // 查询字段类型
            FunctionFieldType functionFieldType = new FunctionFieldType();
            functionFieldType.setFunctionId(functionId);
            functionFieldType.setType(params.getType());
            List<FunctionFieldType> fieldTypeList = this.list(functionFieldType);
            Set<String> fieldTypeSet = new HashSet<>();
            if (CollectionUtil.isNotEmpty(fieldTypeList)) {
                for (FunctionFieldType fieldType : fieldTypeList) {
                    fieldTypeSet.add(fieldType.getFunctionFieldId());
                }
            }
            List<FunctionFieldTypeResultVO> resultList = new ArrayList<>();
            for (FunctionField field : fieldList) {
                Integer type = null;
                if (fieldTypeSet.contains(field.getId())) {
                    type = params.getType();
                }
                resultList.add(new FunctionFieldTypeResultVO(field.getFunctionId(), field.getId(), type, field.getName()));
            }
            return resultList;
        }
        return null;
    }
}
