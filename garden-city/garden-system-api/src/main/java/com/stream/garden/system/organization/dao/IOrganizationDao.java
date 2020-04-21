package com.stream.garden.system.organization.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.organization.model.Organization;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019/7/21 11:35
 */
@Mapper
public interface IOrganizationDao extends IBaseMapper<Organization> {
}
