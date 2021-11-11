package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.RequestLog;
import com.sky.system.dao.RequestLogDao;
import com.sky.system.service.RequestLogService;
import org.springframework.stereotype.Service;

@Service
public class RequestLogServiceImpl extends ServiceImpl<RequestLogDao, RequestLog> implements RequestLogService {
}
