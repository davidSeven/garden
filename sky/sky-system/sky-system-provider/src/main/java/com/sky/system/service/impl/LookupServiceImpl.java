package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.LookupDto;
import com.sky.system.api.dto.LookupQueryDto;
import com.sky.system.api.model.Lookup;
import com.sky.system.dao.LookupDao;
import com.sky.system.service.LookupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupServiceImpl extends ServiceImpl<LookupDao, Lookup> implements LookupService {

    @Override
    public boolean create(LookupDto dto) {
        Lookup lookup = BeanHelpUtil.convertDto(dto, Lookup.class);
        this.exists(lookup);
        return super.save(lookup);
    }

    @Override
    public boolean update(LookupDto dto) {
        Lookup lookup = BeanHelpUtil.convertDto(dto, Lookup.class);
        this.exists(lookup);
        return super.save(lookup);
    }

    private void exists(Lookup lookup) {
        LambdaQueryWrapper<Lookup> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Lookup::getCode, lookup.getCode());
        if (null != lookup.getId()) {
            queryWrapper.ne(Lookup::getId, lookup.getId());
        }
        if (super.count(queryWrapper) > 0) {
            throw new CommonException(500, "system.lookup.codeExists", lookup.getCode());
        }
    }

    @Override
    public IPage<Lookup> page(LookupQueryDto dto) {
        LambdaQueryWrapper<Lookup> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), Lookup::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), Lookup::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), Lookup::getState, dto.getState());
        IPage<Lookup> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<Lookup> list(LookupDto dto) {
        LambdaQueryWrapper<Lookup> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), Lookup::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getName()), Lookup::getName, dto.getName());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), Lookup::getState, dto.getState());
        return super.list(queryWrapper);
    }
}
