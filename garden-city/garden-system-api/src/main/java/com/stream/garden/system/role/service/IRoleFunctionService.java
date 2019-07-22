package com.stream.garden.system.role.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.vo.RoleFunctionVO;

/**
 * @author garden
 * @date 2019/7/22 22:28
 */
public interface IRoleFunctionService extends IBaseService<RoleFunction, String> {

    int saveRoleFunction(RoleFunctionVO vo) throws ApplicationException;

    int deleteByRoleId(String roleId) throws ApplicationException;
}
