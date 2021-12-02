package com.sky.rec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.rec.api.dto.RecOrderDto;
import com.sky.rec.api.model.RecOrder;

public interface RecOrderService extends IService<RecOrder> {

    RecOrder getByOrderNo(String orderNo);

    boolean create(RecOrderDto dto);

    boolean saveAndSubmit(RecOrderDto dto);

    boolean submit(RecOrderDto dto);

    boolean auth(RecOrderDto dto);
}
