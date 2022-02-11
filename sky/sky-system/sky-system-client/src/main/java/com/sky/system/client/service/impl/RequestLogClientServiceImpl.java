package com.sky.system.client.service.impl;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.RequestLog;
import com.sky.system.client.feign.RequestLogClientFeign;
import com.sky.system.client.service.RequestLogClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestLogClientServiceImpl implements RequestLogClientService {

    @Autowired
    private RequestLogClientFeign requestLogClientFeign;

    @Override
    public boolean save(RequestLog requestLog) {
        return ResponseDto.getDataBAndException(this.requestLogClientFeign.save(requestLog));
    }
}
