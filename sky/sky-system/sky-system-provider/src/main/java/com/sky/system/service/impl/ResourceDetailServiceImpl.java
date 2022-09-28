package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.ResourceDetail;
import com.sky.system.dao.ResourceDetailDao;
import com.sky.system.service.ResourceDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceDetailServiceImpl extends ServiceImpl<ResourceDetailDao, ResourceDetail> implements ResourceDetailService {

    @Override
    public boolean refreshResourceDetail(Long menuId, Long resourceApplicationId, List<ResourceDetail> resourceDetailList) {
        // 删除
        LambdaQueryWrapper<ResourceDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ResourceDetail::getMenuId, menuId);
        queryWrapper.eq(ResourceDetail::getResourceApplicationId, resourceApplicationId);
        super.baseMapper.physicalDelete(queryWrapper);
        // 新增
        if (CollectionUtils.isNotEmpty(resourceDetailList)) {
            for (ResourceDetail resourceDetail : resourceDetailList) {
                resourceDetail.setMenuId(menuId);
                resourceDetail.setResourceApplicationId(resourceApplicationId);
            }
            super.saveBatch(resourceDetailList);
        }
        return true;
    }
}
