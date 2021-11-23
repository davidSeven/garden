package com.sky.del.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.del.api.model.OrderDelete;

import java.util.List;

public interface OrderDeleteService extends IService<OrderDelete> {

    void add(String orderNo);

    void add(List<String> orderNos);

    int execute(OrderDelete orderDelete);

    void success(Long id);

    void fail(Long id, String failMessage, int handleSize, long requestConsumeTime);
}
