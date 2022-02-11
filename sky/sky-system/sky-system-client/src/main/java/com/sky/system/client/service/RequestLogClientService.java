package com.sky.system.client.service;

import com.sky.system.api.model.RequestLog;

public interface RequestLogClientService {

    boolean save(RequestLog requestLog);
}
