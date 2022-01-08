package com.sky.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.api.model.User;

/**
 * @date 2020-10-28 028 13:48
 */
public interface UserService extends IService<User> {

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

    /**
     * 根据code查询名称
     *
     * @param code code
     * @return String
     */
    String getNameByCode(String code);
}
