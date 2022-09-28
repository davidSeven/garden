package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @date 2020-10-28 028 13:52
 */
public interface UserRemoteService {

    @PostMapping(value = "/user/get")
    ResponseDto<UserDto> get(@RequestBody UserDto dto,
                             @RequestParam(value = "code", required = false, defaultValue = "none") String code,
                             @RequestParam(value = "id", required = false, defaultValue = "0") Long id);

    @PostMapping(value = "/user/save")
    ResponseDto<Boolean> save(@RequestBody UserDto dto);

    @PostMapping(value = "/user/update")
    ResponseDto<Boolean> update(@RequestBody UserDto dto);

    @PostMapping(value = "/user/pageList")
    ResponseDto<IPage<UserDto>> pageList(@RequestBody UserQueryDto queryDto);

    @PostMapping(value = "/user/getNameByCode")
    ResponseDto<String> getNameByCode(@RequestBody String code);
}
