package com.sky.base.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;

import java.util.List;

public interface CustomerClientService {

    Customer get(Long id);

    Customer getByCode(String code);

    boolean create(CustomerDto dto);

    boolean update(CustomerDto dto);

    boolean delete(CustomerDto dto);

    IPage<Customer> page(CustomerQueryDto dto);

    List<Customer> list(CustomerDto dto);
}
