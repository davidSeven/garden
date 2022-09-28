package com.sky.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.ResourceDataScope;
import com.sky.system.dao.ResourceDataScopeDao;
import com.sky.system.service.ResourceDataScopeService;
import org.springframework.stereotype.Service;

@Service
public class ResourceDataScopeServiceImpl extends ServiceImpl<ResourceDataScopeDao, ResourceDataScope> implements ResourceDataScopeService {
}
