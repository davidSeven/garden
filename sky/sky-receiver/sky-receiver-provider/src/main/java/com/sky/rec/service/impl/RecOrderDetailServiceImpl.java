package com.sky.rec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.rec.api.model.RecOrderDetail;
import com.sky.rec.dao.RecOrderDetailDao;
import com.sky.rec.service.RecOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class RecOrderDetailServiceImpl extends ServiceImpl<RecOrderDetailDao, RecOrderDetail> implements RecOrderDetailService {
}
