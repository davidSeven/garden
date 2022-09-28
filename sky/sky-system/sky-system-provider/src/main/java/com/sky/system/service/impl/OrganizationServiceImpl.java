package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.OrganizationDto;
import com.sky.system.api.model.Organization;
import com.sky.system.api.vo.OrganizationVO;
import com.sky.system.dao.OrganizationDao;
import com.sky.system.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {

    @Override
    public boolean save(OrganizationDto dto) {
        Organization organization = BeanHelpUtil.convertDto(dto, Organization.class);
        // 验证编码是否唯一
        this.exists(organization);
        return super.save(organization);
    }

    @Override
    public boolean update(OrganizationDto dto) {
        Organization organization = BeanHelpUtil.convertDto(dto, Organization.class);
        this.exists(organization);
        return super.updateById(organization);
    }

    private void exists(Organization organization) {
        LambdaQueryWrapper<Organization> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Organization::getCode, organization.getCode());
        if (null != organization.getParentId() && organization.getParentId() > 0) {
            queryWrapper.eq(Organization::getParentId, organization.getParentId());
        }
        if (null != organization.getId()) {
            queryWrapper.ne(Organization::getId, organization.getId());
        }
        if (super.count(queryWrapper) > 0) {
            throw new CommonException(500, "system.organization.codeExists", organization.getCode());
        }
    }

    @Override
    public List<OrganizationVO> tree(OrganizationDto dto) {
        // 查询所有
        LambdaQueryWrapper<Organization> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Organization::getSort);
        List<Organization> list = super.list(queryWrapper);
        // 根节点
        List<OrganizationVO> rootList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Organization organization = list.get(i);
            if (null == organization.getParentId() || 0L == organization.getParentId()) {
                rootList.add(BeanHelpUtil.convertDto(organization, OrganizationVO.class));
                list.remove(i);
                i--;
            }
        }
        for (OrganizationVO root : rootList) {
            transform(root, list);
        }
        return rootList;
    }

    private void transform(OrganizationVO root, List<Organization> list) {
        List<OrganizationVO> children = new ArrayList<>();
        for (Organization organization : list) {
            if (root.getId().equals(organization.getParentId())) {
                OrganizationVO node = BeanHelpUtil.convertDto(organization, OrganizationVO.class);
                transform(node, list);
                children.add(node);
            }
        }
        if (children.size() > 0) {
            root.setChildren(children);
        }
    }

    @Override
    public int physicalDeleteById(Long id) {
        Organization organization = super.getById(id);
        if (null != organization) {
            LambdaQueryWrapper<Organization> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(Organization::getParentId, id);
            if (super.count(queryWrapper) > 0) {
                throw new CommonException(500, "system.organization.delete.hasChildren", organization.getCode(), organization.getName());
            }
            return this.baseMapper.physicalDeleteById(id);
        }
        return 0;
    }
}
