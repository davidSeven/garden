package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.model.ResourceDetail;

import java.util.List;

public interface ResourceDetailService extends IService<ResourceDetail> {

    boolean refreshResourceDetail(Long menuId, Long resourceApplicationId, List<ResourceDetail> resourceDetailList);
}
