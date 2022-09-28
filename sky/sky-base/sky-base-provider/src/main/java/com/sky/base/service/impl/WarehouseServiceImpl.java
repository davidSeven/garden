package com.sky.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.base.api.dto.WarehouseDto;
import com.sky.base.api.dto.WarehouseQueryDto;
import com.sky.base.api.model.Warehouse;
import com.sky.base.dao.WarehouseDao;
import com.sky.base.service.WarehouseService;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, Warehouse> implements WarehouseService {

    @Override
    public Warehouse getByCode(String code) {
        LambdaQueryWrapper<Warehouse> queryWrapper = Wrappers.<Warehouse>lambdaQuery().eq(Warehouse::getWarehouseCode, code);
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean create(WarehouseDto dto) {
        Warehouse warehouse = BeanHelpUtil.convertDto(dto, Warehouse.class);
        this.exists(warehouse);
        return super.save(warehouse);
    }

    @Override
    public boolean update(WarehouseDto dto) {
        Warehouse warehouse = BeanHelpUtil.convertDto(dto, Warehouse.class);
        this.exists(warehouse);
        return super.updateById(warehouse);
    }

    private void exists(Warehouse warehouse) {
        LambdaQueryWrapper<Warehouse> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Warehouse::getWarehouseCode, warehouse.getWarehouseCode());
        if (null != warehouse.getId()) {
            queryWrapper.ne(Warehouse::getId, warehouse.getId());
        }
        if (super.count(queryWrapper) > 0) {
            throw new CommonException(500, "编码重复");
        }
    }

    @Override
    public IPage<Warehouse> page(WarehouseQueryDto dto) {
        LambdaQueryWrapper<Warehouse> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getWarehouseCode()), Warehouse::getWarehouseCode, dto.getWarehouseCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getWarehouseCodeLike()), Warehouse::getWarehouseCode, dto.getWarehouseCodeLike());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getWarehouseName()), Warehouse::getWarehouseName, dto.getWarehouseName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), Warehouse::getState, dto.getState());
        IPage<Warehouse> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<Warehouse> list(WarehouseDto dto) {
        LambdaQueryWrapper<Warehouse> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getWarehouseCode()), Warehouse::getWarehouseCode, dto.getWarehouseCode());
        queryWrapper.in(CollectionUtils.isNotEmpty(dto.getWarehouseCodeList()), Warehouse::getWarehouseCode, dto.getWarehouseCodeList());
        return super.list(queryWrapper);
    }
}
