package com.sky.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;

import java.util.List;

public interface CustomerService extends IService<Customer> {

    Customer getByCode(String code);

    boolean create(CustomerDto dto);

    boolean update(CustomerDto dto);

    IPage<Customer> page(CustomerQueryDto dto);

    List<Customer> list(CustomerDto dto);
}
