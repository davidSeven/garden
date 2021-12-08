package com.sky.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.base.api.constant.CustomerConstant;
import com.sky.base.api.dto.CustomerDto;
import com.sky.base.api.dto.CustomerQueryDto;
import com.sky.base.api.model.Customer;
import com.sky.base.dao.CustomerDao;
import com.sky.base.service.CustomerService;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.client.service.SerialNumberClientService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {

    @Autowired
    private SerialNumberClientService serialNumberClientService;

    @Override
    public Customer getByCode(String code) {
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerCode, code);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean create(CustomerDto dto) {
        Customer customer = BeanHelpUtil.convertDto(dto, Customer.class);
        String code = serialNumberClientService.generateNumber(CustomerConstant.CUSTOMER_CODE);
        customer.setCustomerCode(code);
        return super.save(customer);
    }

    @Override
    public boolean update(CustomerDto dto) {
        Customer customer = BeanHelpUtil.convertDto(dto, Customer.class);
        return super.updateById(customer);
    }

    @Override
    public IPage<Customer> page(CustomerQueryDto dto) {
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCustomerCode()), Customer::getCustomerCode, dto.getCustomerCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCustomerCodeLike()), Customer::getCustomerCode, dto.getCustomerCodeLike());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCustomerName()), Customer::getCustomerName, dto.getCustomerName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), Customer::getState, dto.getState());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getEmail()), Customer::getEmail, dto.getEmail());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getMobilePhone()), Customer::getMobilePhone, dto.getMobilePhone());
        IPage<Customer> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<Customer> list(CustomerDto dto) {
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getCustomerCode()), Customer::getCustomerCode, dto.getCustomerCode());
        queryWrapper.in(CollectionUtils.isNotEmpty(dto.getCustomerCodeList()), Customer::getCustomerCode, dto.getCustomerCodeList());
        return super.list(queryWrapper);
    }
}
