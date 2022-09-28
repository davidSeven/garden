package com.sky.del.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.del.api.model.OrderDetail;
import com.sky.del.dao.OrderDetailDao;
import com.sky.del.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {

    @Override
    public List<OrderDetail> listByOrderNo(String orderNo) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(OrderDetail::getOrderNo, orderNo);
        queryWrapper.orderByAsc(OrderDetail::getLineNo);
        return this.list(queryWrapper);
    }

    @Override
    public int physicalDelete(String orderNo) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(OrderDetail::getOrderNo, orderNo);
        return this.baseMapper.physicalDelete(queryWrapper);
    }
}
