package com.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * @return Page
     */
    @PostMapping(value = "/system/user/page")
    ResponseDto<Page<UserDto>> page(@RequestBody UserDto dto);
}
