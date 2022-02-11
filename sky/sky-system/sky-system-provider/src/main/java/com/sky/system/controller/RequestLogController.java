package com.sky.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.RequestLogQueryDto;
import com.sky.system.api.model.RequestLog;
import com.sky.system.api.remote.RequestLogRemoteService;
import com.sky.system.service.RequestLogService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "请求日志信息")
@RestController
public class RequestLogController implements RequestLogRemoteService {

    @Autowired
    private RequestLogService requestLogService;

    @Override
    public ResponseDto<RequestLog> get(Long id) {
        return new ResponseDto<>(this.requestLogService.getById(id));
    }

    @Override
    public ResponseDto<Boolean> save(RequestLog requestLog) {
        return new ResponseDto<>(this.requestLogService.save(requestLog));
    }

    @Override
    public ResponseDto<Boolean> update(RequestLog requestLog) {
        return new ResponseDto<>(this.requestLogService.updateById(requestLog));
    }

    @Override
    public ResponseDto<IPage<RequestLog>> page(RequestLogQueryDto dto) {
        IPage<RequestLog> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapper<RequestLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getTraceId()), RequestLog::getTraceId, dto.getTraceId().trim());
        queryWrapper.eq(null != dto.getSpanId(), RequestLog::getSpanId, dto.getSpanId());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getRequestDomain()), RequestLog::getRequestDomain, dto.getRequestDomain().trim());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getRequestUri()), RequestLog::getRequestUri, dto.getRequestUri().trim());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getRequestMethod()), RequestLog::getRequestMethod, dto.getRequestMethod().trim());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getRequestIp()), RequestLog::getRequestIp, dto.getRequestIp().trim());
        return new ResponseDto<IPage<RequestLog>>().ok().setData(this.requestLogService.page(page, queryWrapper));
    }
}
