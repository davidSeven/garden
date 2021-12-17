package com.sky.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.LookupItemDto;
import com.sky.system.api.dto.LookupItemQueryDto;
import com.sky.system.api.model.LookupItem;

import java.util.List;

public interface LookupItemService extends IService<LookupItem> {

    boolean create(LookupItemDto dto);

    boolean update(LookupItemDto dto);

    IPage<LookupItem> page(LookupItemQueryDto dto);

    List<LookupItem> list(LookupItemDto dto);
}
