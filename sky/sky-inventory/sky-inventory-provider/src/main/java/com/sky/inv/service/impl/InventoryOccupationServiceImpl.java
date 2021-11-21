package com.sky.inv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.inv.api.model.InventoryOccupation;
import com.sky.inv.dao.InventoryOccupationDao;
import com.sky.inv.service.InventoryOccupationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryOccupationServiceImpl extends ServiceImpl<InventoryOccupationDao, InventoryOccupation> implements InventoryOccupationService {

    @Transactional
    @Override
    public int physicalDeleteById(Long id) {
        return this.baseMapper.physicalDeleteById(id);
    }
}
