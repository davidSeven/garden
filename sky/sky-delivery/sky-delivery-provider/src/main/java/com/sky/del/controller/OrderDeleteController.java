package com.sky.del.controller;

import com.sky.del.api.model.OrderDelete;
import com.sky.del.service.OrderDeleteService;
import com.sky.framework.api.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "出库单")
@ApiSort(100)
@RestController
public class OrderDeleteController {

    @Autowired
    private OrderDeleteService orderDeleteService;

    @PostMapping(value = "/delivery/delete")
    public ResponseDto<Integer> deliveryDelete(@RequestBody @Validated OrderDelete orderDelete) {
        return new ResponseDto<>(this.orderDeleteService.execute(orderDelete));
    }
}
