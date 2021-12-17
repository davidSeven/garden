package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.constant.I18nConstant;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;
import com.sky.system.dao.I18nDao;
import com.sky.system.service.DictionaryService;
import com.sky.system.service.I18nService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class I18nServiceImpl extends ServiceImpl<I18nDao, I18n> implements I18nService {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public boolean create(I18nDto dto) {
        I18n i18n = BeanHelpUtil.convertDto(dto, I18n.class);
        return super.save(i18n);
    }

    @Override
    public boolean update(I18nDto dto) {
        I18n i18n = BeanHelpUtil.convertDto(dto, I18n.class);
        return super.updateById(i18n);
    }

    @Override
    public IPage<I18n> page(I18nQueryDto dto) {
        LambdaQueryWrapper<I18n> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), I18n::getState, dto.getState());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getLanguageType()), I18n::getLanguageType, dto.getLanguageType());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), I18n::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getValue()), I18n::getValue, dto.getValue());
        IPage<I18n> iPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        return super.page(iPage, queryWrapper);
    }

    @Override
    public List<I18n> list(I18nDto dto) {
        LambdaQueryWrapper<I18n> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getState()), I18n::getState, dto.getState());
        queryWrapper.eq(StringUtils.isNotEmpty(dto.getLanguageType()), I18n::getLanguageType, dto.getLanguageType());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getCode()), I18n::getCode, dto.getCode());
        queryWrapper.like(StringUtils.isNotEmpty(dto.getValue()), I18n::getValue, dto.getValue());
        return super.list(queryWrapper);
    }

    @Override
    public List<Locale> getLocaleList() {
        List<Locale> localeList = new ArrayList<>();
        localeList.add(Locale.SIMPLIFIED_CHINESE);
        localeList.add(Locale.ENGLISH);
        return localeList;
    }

    @Override
    public I18n get(String code, Locale locale) {
        LambdaQueryWrapper<I18n> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(I18n::getCode, I18n::getValue, I18n::getLanguageType);
        if (null != locale) {
            queryWrapper.eq(I18n::getLanguageType, locale.toLanguageTag());
        }
        queryWrapper.eq(I18n::getCode, code);
        queryWrapper.eq(I18n::getState, I18nConstant.State.$1.getCode());
        List<I18n> i18nList = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(i18nList)) {
            return null;
        }
        return i18nList.get(0);
    }

    @Override
    public List<I18n> getList(String code) {
        LambdaQueryWrapper<I18n> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(I18n::getCode, I18n::getValue, I18n::getLanguageType);
        queryWrapper.eq(I18n::getCode, code);
        queryWrapper.eq(I18n::getState, I18nConstant.State.$1.getCode());
        return super.list(queryWrapper);
    }
}
