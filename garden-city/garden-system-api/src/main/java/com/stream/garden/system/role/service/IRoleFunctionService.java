package com.stream.garden.system.role.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;
import com.stream.garden.system.role.model.RoleFunction;
import com.stream.garden.system.role.vo.MenuFunctionVO;
import com.stream.garden.system.role.vo.RoleMenuVO;

import java.util.List;

/**
 * @author garden
 * @date 2019/7/22 22:28
 */
public interface IRoleFunctionService extends IBaseService<RoleFunction, String> {

    int deleteByRoleId(String roleId) throws ApplicationException;

    List<MenuFunctionVO> getMenuFunction(String roleId) throws ApplicationException;

    void saveMenuFunction(RoleMenuVO vo) throws ApplicationException;
}
