package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.GenerateNumberDto;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.model.SerialNumber;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SerialNumberRemoteService {

    @GetMapping(value = "/serial-number/{id}")
    ResponseDto<SerialNumber> get(@PathVariable(value = "id") Long id);

    @PostMapping(value = "/serial-number")
    ResponseDto<Boolean> save(@RequestBody SerialNumber serialNumber);

    @PutMapping(value = "/serial-number")
    ResponseDto<Boolean> update(@RequestBody SerialNumber serialNumber);

    @PostMapping(value = "/serial-number/page")
    ResponseDto<IPage<SerialNumber>> page(@RequestBody SerialNumberQueryDto dto);

    @PostMapping(value = "/serial-number/generateNumber")
    ResponseDto<String> generateNumber(@RequestBody GenerateNumberDto dto);

    @PostMapping(value = "/serial-number/generateNumbers")
    ResponseDto<List<String>> generateNumbers(@RequestBody GenerateNumberDto dto);
}
