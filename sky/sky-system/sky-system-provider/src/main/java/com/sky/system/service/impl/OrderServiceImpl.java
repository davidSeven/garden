package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.Order;
import com.sky.system.dao.OrderDao;
import com.sky.system.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {
}
