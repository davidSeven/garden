package com.sky.del.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.del.api.dto.OrderDeleteDto;
import com.sky.del.api.dto.OrderDto;
import com.sky.del.api.model.Order;
import com.sky.del.api.remote.OrderRemoteService;
import com.sky.del.service.OrderService;
import com.sky.framework.api.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "出库单")
@ApiSort(100)
@RestController
public class OrderController implements OrderRemoteService {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseDto<Boolean> create(OrderDto dto) {
        return new ResponseDto<>(this.orderService.create(dto));
    }

    @Override
    public ResponseDto<Boolean> update(OrderDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> delete(OrderDeleteDto dto) {
        return new ResponseDto<>(this.orderService.delete(dto));
    }

    @Override
    public ResponseDto<IPage<Order>> list(Order order) {
        return null;
    }
}
