package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.model.Dictionary;
import com.sky.system.api.vo.DictionaryVO;
import com.sky.system.dao.DictionaryDao;
import com.sky.system.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        // 验证编码是否唯一
        this.exists(dictionary);
        boolean b = super.save(dictionary);
        Long parentId = dictionary.getParentId();
        if (b && (null != parentId && parentId > 0)) {
            super.baseMapper.increaseChildrenSize(parentId);
        }
        return b;
    }

    @Transactional
    @Override
    public boolean update(DictionaryDto dto) {
        Dictionary dictionary = BeanHelpUtil.convertDto(dto, Dictionary.class);
        this.exists(dictionary);
        return super.updateById(dictionary);
    }

    private void exists(Dictionary dictionary) {
        LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Dictionary::getCode, dictionary.getCode());
        if (null != dictionary.getParentId() && dictionary.getParentId() > 0) {
            queryWrapper.eq(Dictionary::getParentId, dictionary.getParentId());
        }
        if (null != dictionary.getId()) {
            queryWrapper.ne(Dictionary::getId, dictionary.getId());
        }
        if (super.count(queryWrapper) > 0) {
            throw new CommonException(500, "system.dictionary.codeExists", dictionary.getCode());
        }
    }

    @Override
    public List<DictionaryVO> listDictionary(DictionaryDto dto) {
        LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
        if (null == dto.getParentId()) {
            queryWrapper.eq(Dictionary::getParentId, 0L);
        } else {
            queryWrapper.eq(Dictionary::getParentId, dto.getParentId());
        }
        List<Dictionary> list = super.list(queryWrapper);
        return BeanHelpUtil.convertDtoList(list, DictionaryVO.class);
    }

    @Override
    public List<DictionaryVO> tree(DictionaryDto dto) {
        // 查询所有
        LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Dictionary::getSort);
        List<Dictionary> list = super.list(queryWrapper);
        // 根节点
        List<DictionaryVO> rootList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Dictionary dictionary = list.get(i);
            if (null == dictionary.getParentId() || 0L == dictionary.getParentId()) {
                rootList.add(BeanHelpUtil.convertDto(dictionary, DictionaryVO.class));
                list.remove(i);
                i--;
            }
        }
        for (DictionaryVO root : rootList) {
            transform(root, list);
        }
        return rootList;
    }

    private void transform(DictionaryVO root, List<Dictionary> list) {
        List<DictionaryVO> children = new ArrayList<>();
        for (Dictionary dictionary : list) {
            if (root.getId().equals(dictionary.getParentId())) {
                DictionaryVO node = BeanHelpUtil.convertDto(dictionary, DictionaryVO.class);
                transform(node, list);
                children.add(node);
            }
        }
        if (children.size() > 0) {
            root.setChildren(children);
        }
    }

    @Transactional
    @Override
    public int physicalDeleteById(Long id) {
        Dictionary dictionary = super.getById(id);
        if (null != dictionary) {
            LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(Dictionary::getParentId, id);
            if (super.count(queryWrapper) > 0) {
                throw new CommonException(500, "system.dictionary.delete.hasChildren", dictionary.getCode(), dictionary.getName());
            }
            int i = this.baseMapper.physicalDeleteById(id);
            Long parentId = dictionary.getParentId();
            if (i > 0 && (null != parentId && parentId > 0)) {
                super.baseMapper.decreaseChildrenSize(parentId);
            }
            return i;
        }
        return 0;
    }

    @Override
    public String getValue(String code) {
        LambdaQueryWrapper<Dictionary> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Dictionary::getRoute, code);
        Dictionary dictionary = super.getOne(queryWrapper);
        if (null != dictionary) {
            return dictionary.getValue();
        }
        return null;
    }
}
