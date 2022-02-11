package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.ResourceField;
import com.sky.system.dao.ResourceFieldDao;
import com.sky.system.service.ResourceFieldService;
import org.springframework.stereotype.Service;

@Service
public class ResourceFieldServiceImpl extends ServiceImpl<ResourceFieldDao, ResourceField> implements ResourceFieldService {
}
