package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.LookupItemDto;
import com.sky.system.api.dto.LookupItemQueryDto;
import com.sky.system.api.dto.LookupItemSaveDto;
import com.sky.system.api.model.LookupItem;
import com.sky.system.dao.LookupItemDao;
import com.sky.system.service.LookupItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupItemServiceImpl extends ServiceImpl<LookupItemDao, LookupItem> implements LookupItemService {

    @Override
    public boolean create(LookupItemDto dto) {
        LookupItem lookupItem = BeanHelpUtil.convertDto(dto, LookupItem.class);
        return super.save(lookupItem);
    }

    @Override
    public boolean update(LookupItemDto dto) {
        LookupItem lookupItem = BeanHelpUtil.convertDto(dto, LookupItem.class);
        return super.save(lookupItem);
    }

    @Override
    public IPage<LookupItem> page(LookupItemQueryDto dto) {
        LambdaQueryWrapper<LookupItem> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), LookupItem::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), LookupItem::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), LookupItem::getState, dto.getState());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getLanguageType()), LookupItem::getLanguageType, dto.getLanguageType());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getParentCode()), LookupItem::getParentCode, dto.getParentCode());
        IPage<LookupItem> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<LookupItem> list(LookupItemDto dto) {
        LambdaQueryWrapper<LookupItem> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), LookupItem::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), LookupItem::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), LookupItem::getState, dto.getState());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getLanguageType()), LookupItem::getLanguageType, dto.getLanguageType());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getParentCode()), LookupItem::getParentCode, dto.getParentCode());
        return super.list(queryWrapper);
    }

    @Override
    public boolean save(LookupItemSaveDto dto) {
        List<Long> deleteIds = dto.getDeleteIds();
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            super.removeByIds(deleteIds);
        }
        List<LookupItem> inserts = new ArrayList<>();
        List<LookupItem> updates = new ArrayList<>();
        List<LookupItemDto> itemList = dto.getItemList();
        if (CollectionUtils.isNotEmpty(itemList)) {
            for (LookupItemDto itemDto : itemList) {
                LookupItem lookupItem = BeanHelpUtil.convertDto(itemDto, LookupItem.class);
                if (null == lookupItem.getId() || 0 == lookupItem.getId()) {
                    inserts.add(lookupItem);
                } else {
                    updates.add(lookupItem);
                }
            }
            if (CollectionUtils.isNotEmpty(updates)) {
                super.updateBatchById(updates);
            }
            if (CollectionUtils.isNotEmpty(inserts)) {
                super.saveBatch(inserts);
            }
        }
        return true;
    }
}
