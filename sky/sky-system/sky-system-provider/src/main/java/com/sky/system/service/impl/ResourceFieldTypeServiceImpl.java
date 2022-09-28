package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.ResourceFieldType;
import com.sky.system.dao.ResourceFieldTypeDao;
import com.sky.system.service.ResourceFieldTypeService;
import org.springframework.stereotype.Service;

@Service
public class ResourceFieldTypeServiceImpl extends ServiceImpl<ResourceFieldTypeDao, ResourceFieldType> implements ResourceFieldTypeService {
}
