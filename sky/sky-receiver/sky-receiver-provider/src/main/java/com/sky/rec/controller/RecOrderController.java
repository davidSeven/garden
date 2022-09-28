package com.sky.rec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.netflix.discovery.converters.Auto;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.rec.api.dto.RecOrderDto;
import com.sky.rec.api.model.RecOrder;
import com.sky.rec.api.remote.RecOrderRemoteService;
import com.sky.rec.service.RecOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "入库单")
@ApiSort(100)
@RestController
public class RecOrderController implements RecOrderRemoteService {

    @Autowired
    private RecOrderService orderService;

    @Override
    public ResponseDto<Boolean> create(RecOrderDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> update(RecOrderDto dto) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> delete(RecOrderDto dto) {
        return null;
    }

    @Override
    public ResponseDto<IPage<RecOrder>> list(RecOrder order) {
        return null;
    }

    @Override
    public ResponseDto<Boolean> audit(RecOrderDto dto) {
        return null;
    }
}
