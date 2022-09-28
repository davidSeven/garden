package com.sky.del.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.del.api.model.OrderDetail;

import java.util.List;

public interface OrderDetailService extends IService<OrderDetail> {

    List<OrderDetail> listByOrderNo(String orderNo);

    int physicalDelete(String orderNo);
}
