package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.OrganizationDto;
import com.sky.system.api.model.Organization;
import com.sky.system.api.vo.OrganizationVO;

import java.util.List;

public interface OrganizationService extends IService<Organization> {

    /**
     * 保存
     *
     * @param dto dto
     * @return boolean
     */
    boolean save(OrganizationDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return boolean
     */
    boolean update(OrganizationDto dto);

    /**
     * 查询
     *
     * @param dto dto
     * @return OrganizationVO
     */
    List<OrganizationVO> tree(OrganizationDto dto);

    /**
     * 物理删除
     *
     * @param id id
     * @return int
     */
    int physicalDeleteById(Long id);
}
