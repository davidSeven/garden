package com.stream.garden.system.role.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import com.stream.garden.system.role.dao.IRoleFunctionDao;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.service.IRoleFunctionService;
import com.stream.garden.system.role.vo.RoleFunctionVO;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019/7/22 22:29
 */
@Service
public class RoleFunctionServiceImpl extends AbstractBaseService<RoleFunction, String> implements IRoleFunctionService {

    public RoleFunctionServiceImpl(IRoleFunctionDao iRoleFunctionDao) {
        super(iRoleFunctionDao);
    }

    public IRoleFunctionDao getMapper() {
        return (IRoleFunctionDao) super.baseMapper;
    }

    @Override
    public int saveRoleFunction(RoleFunctionVO vo) throws ApplicationException {
        // 先删后增
        this.deleteByRoleId(vo.getRoleId());
        if (CollectionUtil.isNotEmpty(vo.getRoleFunctionList())) {
            for (RoleFunction roleFunction : vo.getRoleFunctionList()) {
                roleFunction.setRoleId(vo.getRoleId());
            }
            return this.insertBatch(vo.getRoleFunctionList());
        }
        return 0;
    }

    @Override
    public int deleteByRoleId(String roleId) throws ApplicationException {
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setRoleId(roleId);
        return this.getMapper().deleteByRoleId(roleFunction);
    }
}
