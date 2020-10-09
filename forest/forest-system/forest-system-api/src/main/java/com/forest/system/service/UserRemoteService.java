package com.forest.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forest.framework.dto.ResponseDto;
import com.forest.system.dto.UserDto;
import com.forest.system.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @date 2020-09-22 022 14:41
 */
public interface UserRemoteService {

    /**
     * 新增
     *
     * @param dto dto
     * @return Boolean
     */
    @PostMapping(value = "/system/user/add")
    ResponseDto<Boolean> save(@RequestBody UserDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Boolean
     */
    @PostMapping(value = "/system/user/update")
    ResponseDto<Boolean> update(@RequestBody UserDto dto);

    /**
     * 分页
     *
     * @param dto dto
     * @return IPage
     */
    @PostMapping(value = "/system/user/page")
    ResponseDto<IPage<User>> page(@RequestBody UserDto dto);
}
