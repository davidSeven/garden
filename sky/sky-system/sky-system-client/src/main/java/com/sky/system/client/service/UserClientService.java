package com.sky.system.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;

/**
 * @date 2020-10-28 028 14:53
 */
public interface UserClientService {

    /**
     * 查询
     *
     * @param id id
     * @return UserDto
     */
    UserDto get(Long id);

    /**
     * 保存
     *
     * @param dto dto
     * @return boolean
     */
    boolean save(UserDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return boolean
     */
    boolean update(UserDto dto);

    /**
     * 分页
     *
     * @param queryDto queryDto
     * @return UserDto
     */
    IPage<UserDto> pageList(UserQueryDto queryDto);

}
