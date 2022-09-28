package com.sky.del.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.del.api.dto.OrderDeleteDto;
import com.sky.del.api.dto.OrderDto;
import com.sky.del.api.model.Order;

public interface OrderService extends IService<Order> {

    boolean create(OrderDto dto);

    boolean delete(OrderDeleteDto dto);

    void delete(String orderNo);
}
