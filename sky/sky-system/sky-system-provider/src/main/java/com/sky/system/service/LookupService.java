package com.sky.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.LookupDto;
import com.sky.system.api.dto.LookupQueryDto;
import com.sky.system.api.model.Lookup;

import java.util.List;

public interface LookupService extends IService<Lookup> {

    boolean create(LookupDto dto);

    boolean update(LookupDto dto);

    IPage<Lookup> page(LookupQueryDto dto);

    List<Lookup> list(LookupDto dto);
}
