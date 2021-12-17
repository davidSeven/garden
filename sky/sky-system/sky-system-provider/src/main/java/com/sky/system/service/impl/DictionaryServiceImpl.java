package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.model.Dictionary;
import com.sky.system.dao.DictionaryDao;
import com.sky.system.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @date 2020-11-03 003 10:00
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {

    @Transactional
    @Override
    public boolean save(DictionaryDto dto) {
        Dictionary dictionary = BeanHelpUtil.convertDto(dto, Dictionary.class);
        return super.save(dictionary);
    }

    @Transactional
    @Override
    public boolean update(DictionaryDto dto) {
        Dictionary dictionary = BeanHelpUtil.convertDto(dto, Dictionary.class);
        return super.updateById(dictionary);
    }

    @Override
    public List<DictionaryDto> listDictionary(DictionaryDto dto) {
        LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
        if (null == dto.getParentId()) {
            queryWrapper.eq(Dictionary::getParentId, 0L);
        } else {
            queryWrapper.eq(Dictionary::getParentId, dto.getParentId());
        }
        List<Dictionary> list = super.list(queryWrapper);
        return BeanHelpUtil.convertDtoList(list, DictionaryDto.class);
    }

    @Transactional
    @Override
    public int physicalDeleteById(Long id) {
        return this.baseMapper.physicalDeleteById(id);
    }
}
