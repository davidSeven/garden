package com.sky.base.client.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;
import com.sky.base.client.feign.CustomerClientFeign;
import com.sky.base.client.service.CustomerClientService;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerClientServiceImpl implements CustomerClientService {

    @Autowired
    private CustomerClientFeign customerClientFeign;

    @Override
    public Customer get(Long id) {
        return ResponseDto.getDataAndException(customerClientFeign.get(id));
    }

    @Override
    public Customer getByCode(String code) {
        return ResponseDto.getDataAndException(customerClientFeign.getByCode(code));
    }

    @Override
    public boolean create(CustomerDto dto) {
        return ResponseDto.getDataBAndException(customerClientFeign.create(dto));
    }

    @Override
    public boolean update(CustomerDto dto) {
        return ResponseDto.getDataBAndException(customerClientFeign.update(dto));
    }

    @Override
    public boolean delete(CustomerDto dto) {
        return ResponseDto.getDataBAndException(customerClientFeign.delete(dto));
    }

    @Override
    public IPage<Customer> page(CustomerQueryDto dto) {
        return ResponseDto.getDataAndException(customerClientFeign.page(dto));
    }

    @Override
    public List<Customer> list(CustomerDto dto) {
        return ResponseDto.getDataAndException(customerClientFeign.list(dto));
    }
}
