package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.constant.FunctionFieldTypeConstant;
import com.stream.garden.system.function.model.FunctionField;
import com.stream.garden.system.function.model.FunctionFieldType;
import com.stream.garden.system.function.service.IFunctionFieldService;
import com.stream.garden.system.function.service.IFunctionFieldTypeService;
import com.stream.garden.system.function.vo.FunctionFieldTypeResultVO;
import com.stream.garden.system.role.dao.IRoleFunctionFieldDao;
import com.stream.garden.system.role.model.RoleFunctionField;
import com.stream.garden.system.role.service.IRoleFunctionFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author garden
 * @date 2019/7/22 22:29
 */
@Service
public class RoleFunctionFieldServiceImpl extends AbstractBaseService<RoleFunctionField, String> implements IRoleFunctionFieldService {

    @Autowired
    private IFunctionFieldService functionFieldService;
    @Autowired
    private IFunctionFieldTypeService functionFieldTypeService;

    public RoleFunctionFieldServiceImpl(IRoleFunctionFieldDao iRoleFunctionFieldDao) {
        super(iRoleFunctionFieldDao);
    }

    public IRoleFunctionFieldDao getMapper() {
        return (IRoleFunctionFieldDao) super.baseMapper;
    }

    @Override
    public Map<Integer, List<FunctionFieldTypeResultVO>> listConfig(RoleFunctionField params) throws ApplicationException {
        Map<Integer, List<FunctionFieldTypeResultVO>> map = new HashMap<>();
        // 查询字段
        FunctionField functionField = new FunctionField();
        String functionId = params.getFunctionId();
        functionField.setFunctionId(functionId);
        List<FunctionField> fieldList = this.functionFieldService.list(functionField);
        if (CollectionUtil.isNotEmpty(fieldList)) {
            // 查询字段类型
            FunctionFieldType functionFieldType = new FunctionFieldType();
            functionFieldType.setFunctionId(functionId);
            List<FunctionFieldType> fieldTypeList = this.functionFieldTypeService.list(functionFieldType);
            Set<String> permissionSet = new HashSet<>();
            Set<String> sensitiveSet = new HashSet<>();
            if (CollectionUtil.isNotEmpty(fieldTypeList)) {
                for (FunctionFieldType fieldType : fieldTypeList) {
                    if (FunctionFieldTypeConstant.TYPE_10 == fieldType.getType()) {
                        permissionSet.add(fieldType.getFunctionFieldId());
                    } else if (FunctionFieldTypeConstant.TYPE_20 == fieldType.getType()) {
                        sensitiveSet.add(fieldType.getFunctionFieldId());
                    }
                }
            }
            // 查询是否钩上
            RoleFunctionField roleFunctionField = new RoleFunctionField();
            roleFunctionField.setRoleId(params.getRoleId());
            roleFunctionField.setFunctionId(params.getFunctionId());
            List<RoleFunctionField> roleFunctionFieldList = this.list(roleFunctionField);
            Set<String> rolePermissionSet = new HashSet<>();
            Set<String> roleSensitiveSet = new HashSet<>();
            if (CollectionUtil.isNotEmpty(roleFunctionFieldList)) {
                for (RoleFunctionField field : roleFunctionFieldList) {
                    if (FunctionFieldTypeConstant.TYPE_10 == field.getType()) {
                        rolePermissionSet.add(field.getFunctionFieldId());
                    } else if (FunctionFieldTypeConstant.TYPE_20 == field.getType()) {
                        roleSensitiveSet.add(field.getFunctionFieldId());
                    }
                }
            }
            // 处理返回的字段
            List<FunctionFieldTypeResultVO> permissionList = new ArrayList<>();
            List<FunctionFieldTypeResultVO> sensitiveList = new ArrayList<>();
            for (FunctionField field : fieldList) {
                if (permissionSet.contains(field.getId())) {
                    permissionList.add(new FunctionFieldTypeResultVO(null, field.getId(), null, field.getName(), rolePermissionSet.contains(field.getId())));
                }
                if (sensitiveSet.contains(field.getId())) {
                    sensitiveList.add(new FunctionFieldTypeResultVO(null, field.getId(), null, field.getName(), roleSensitiveSet.contains(field.getId())));
                }
            }
            map.put(FunctionFieldTypeConstant.TYPE_10, permissionList);
            map.put(FunctionFieldTypeConstant.TYPE_20, sensitiveList);
        }
        return map;
    }

    @Override
    public void saveBatch(String roleId, List<RoleFunctionField> list) throws ApplicationException {
        // 先删除
        RoleFunctionField params = new RoleFunctionField();
        params.setRoleId(roleId);
        this.getMapper().deleteByRoleId(params);
        // 保存
        this.insertBatch(list);
    }
}
