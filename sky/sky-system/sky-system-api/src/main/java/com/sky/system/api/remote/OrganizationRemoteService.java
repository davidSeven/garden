package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.OrganizationDto;
import com.sky.system.api.vo.OrganizationVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrganizationRemoteService {

    @PostMapping(value = "/organization")
    ResponseDto<Boolean> save(@RequestBody OrganizationDto dto);

    @PutMapping(value = "/organization")
    ResponseDto<Boolean> update(@RequestBody OrganizationDto dto);

    @PostMapping(value = "/organization/tree")
    ResponseDto<List<OrganizationVO>> tree(@RequestBody OrganizationDto dto);

    @DeleteMapping(value = "/organization")
    ResponseDto<Integer> delete(@RequestBody OrganizationDto dto);
}
