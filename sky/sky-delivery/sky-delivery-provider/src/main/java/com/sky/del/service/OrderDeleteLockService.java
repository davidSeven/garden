package com.sky.del.service;

import com.sky.del.api.model.OrderDelete;

public interface OrderDeleteLockService {

    void execute(OrderDelete orderDelete);
}
