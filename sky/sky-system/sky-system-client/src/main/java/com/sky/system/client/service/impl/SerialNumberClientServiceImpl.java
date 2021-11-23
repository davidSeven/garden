package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.GenerateNumberDto;
import com.sky.system.client.feign.SerialNumberClientFeign;
import com.sky.system.client.service.SerialNumberClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerialNumberClientServiceImpl implements SerialNumberClientService {

    @Autowired
    private SerialNumberClientFeign serialNumberClientFeign;

    @Override
    public String generateNumber(String code) {
        return ResponseDto.getDataAndException(this.serialNumberClientFeign.generateNumber(new GenerateNumberDto(code)));
    }

    @Override
    public List<String> generateNumbers(String code, int num) {
        return ResponseDto.getDataAndException(this.serialNumberClientFeign.generateNumbers(new GenerateNumberDto(code, num)));
    }
}
