package com.sky.inv.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.inv.api.model.InventoryOccupation;

public interface InventoryOccupationService extends IService<InventoryOccupation> {

    /**
     * 物理删除
     *
     * @param id id
     * @return int
     */
    int physicalDeleteById(Long id);
}
