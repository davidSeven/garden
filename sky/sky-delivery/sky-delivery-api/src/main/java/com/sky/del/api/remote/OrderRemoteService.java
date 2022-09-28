package com.sky.del.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.del.api.dto.OrderDeleteDto;
import com.sky.del.api.dto.OrderDto;
import com.sky.del.api.model.Order;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderRemoteService {

    @PostMapping(value = "/delivery")
    ResponseDto<Boolean> create(@RequestBody @Validated OrderDto dto);

    @PutMapping(value = "/delivery")
    ResponseDto<Boolean> update(@RequestBody @Validated OrderDto dto);

    @DeleteMapping(value = "/delivery")
    ResponseDto<Boolean> delete(@RequestBody @Validated OrderDeleteDto dto);

    @PostMapping(value = "/delivery/list")
    ResponseDto<IPage<Order>> list(@RequestBody Order order);
}
