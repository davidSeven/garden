package com.stream.garden.system.organization.service.impl;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.organization.dao.IOrganizationDao;
import com.stream.garden.system.organization.model.Organization;
import com.stream.garden.system.organization.service.IOrganizationService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019/7/21 11:37
 */
@Service
public class OrganizationServiceImpl extends AbstractBaseService<Organization, String> implements IOrganizationService {
    public OrganizationServiceImpl(IOrganizationDao iOrganizationDao) {
        super(iOrganizationDao);
    }

    @Override
    public int insert(Organization organization) throws ApplicationException {
        try {
            // 设置父级id
            if (StringUtils.isEmpty(organization.getParentId())) {
                organization.setParentId("0");
            }
            // 同一个菜单下两个子菜单的名字不能一样
            Organization paramOrganization = new Organization();
            paramOrganization.setName(organization.getName());
            paramOrganization.setParentId(organization.getParentId());
            if (super.exists(paramOrganization)) {
                throw new ApplicationException(SystemExceptionCode.ORGANIZATION_NAME_REPEAT, organization.getName());
            }
            return super.insertSelective(organization);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e);
        }
    }

    @Override
    public int update(Organization organization) throws ApplicationException {
        // 同一个菜单下两个子菜单的名字不能一样
        Organization paramOrganization = new Organization();
        // sql中的条件是<>
        paramOrganization.setId(organization.getId());
        paramOrganization.setName(organization.getName());
        // 设置父级id
        if (StringUtils.isEmpty(organization.getParentId())) {
            organization.setParentId("0");
        }
        paramOrganization.setParentId(organization.getParentId());
        // 根据名字和父级id查询，如果返回的条数大于1，则存在相同的记录
        if (super.exists(paramOrganization)) {
            throw new ApplicationException(SystemExceptionCode.ORGANIZATION_NAME_REPEAT, organization.getName());
        }
        return super.updateSelective(organization);
    }

    @Override
    public int delete(String... strings) throws ApplicationException {
        if (ArrayUtils.isEmpty(strings)) {
            return 0;
        }
        for (String id : strings) {
            Organization paramMenu = new Organization();
            paramMenu.setParentId(id);
            // 根据parentId查询记录，如果存在，则存在自己，则不能删除
            if (super.exists(paramMenu)) {
                throw new ApplicationException(SystemExceptionCode.ORGANIZATION_EXISTS_CHILDREN_DELETE_EXCEPTION);
            }
        }
        return super.delete(strings);
    }
}
