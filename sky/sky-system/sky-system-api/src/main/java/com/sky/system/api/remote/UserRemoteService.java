package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @date 2020-10-28 028 13:52
 */
public interface UserRemoteService {

    @PostMapping(value = "/user/get")
    ResponseDto<UserDto> get(@RequestBody UserDto dto);

    @PostMapping(value = "/user/save")
    ResponseDto<Boolean> save(@RequestBody UserDto dto);

    @PostMapping(value = "/user/update")
    ResponseDto<Boolean> update(@RequestBody UserDto dto);

    @PostMapping(value = "/user/pageList")
    ResponseDto<IPage<UserDto>> pageList(@RequestBody UserQueryDto queryDto);
}
