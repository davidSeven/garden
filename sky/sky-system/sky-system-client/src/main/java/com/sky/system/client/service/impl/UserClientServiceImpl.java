package com.sky.system.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.UserDto;
import com.sky.system.api.dto.UserQueryDto;
import com.sky.system.client.feign.UserClientFeign;
import com.sky.system.client.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2020-10-28 028 15:08
 */
@Service
public class UserClientServiceImpl implements UserClientService {

    @Autowired
    private UserClientFeign userClientFeign;

    @Override
    public UserDto get(Long id) {
        UserDto dto = new UserDto();
        dto.setId(id);
        return ResponseDto.getData(this.userClientFeign.get(dto, null, null));
    }

    @Override
    public boolean save(UserDto dto) {
        return ResponseDto.getDataB(this.userClientFeign.save(dto));
    }

    @Override
    public boolean update(UserDto dto) {
        return ResponseDto.getDataB(this.userClientFeign.update(dto));
    }

    @Override
    public IPage<UserDto> pageList(UserQueryDto queryDto) {
        return ResponseDto.getData(this.userClientFeign.pageList(queryDto));
    }
}
