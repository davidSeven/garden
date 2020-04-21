package com.stream.garden.dictionary.service.impl;

import com.stream.garden.dictionary.dao.IDictionaryDao;
import com.stream.garden.dictionary.exception.DictionaryExceptionCode;
import com.stream.garden.dictionary.model.Dictionary;
import com.stream.garden.dictionary.service.IDictionaryService;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-09-29 13:39
 */
@Service
public class DictionaryService extends AbstractBaseService<Dictionary, String, IDictionaryDao> implements IDictionaryService {

    @Override
    public int insert(Dictionary dictionary) throws ApplicationException {
        try {
            // 设置父级id
            if (StringUtils.isEmpty(dictionary.getParentId())) {
                dictionary.setParentId("0");
            }
            // 编码不能相同
            Dictionary paramDictionary = new Dictionary();
            paramDictionary.setCode(dictionary.getCode());
            paramDictionary.setParentId(dictionary.getParentId());
            if (super.exists(paramDictionary)) {
                throw new ApplicationException(DictionaryExceptionCode.DICTIONARY_ADD_REPEAT, dictionary.getCode());
            }
            return super.insertSelective(dictionary);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e);
        }
    }

    @Override
    public int update(Dictionary dictionary) throws ApplicationException {
        // 编码不能相同
        Dictionary paramDictionary = new Dictionary();
        // sql中的条件是<>
        paramDictionary.setId(dictionary.getId());
        paramDictionary.setCode(dictionary.getCode());
        // 设置父级id
        if (StringUtils.isEmpty(dictionary.getParentId())) {
            dictionary.setParentId("0");
        }
        paramDictionary.setParentId(dictionary.getParentId());
        // 根据名字和父级id查询，如果返回的条数大于1，则存在相同的记录
        if (super.exists(paramDictionary)) {
            throw new ApplicationException(DictionaryExceptionCode.DICTIONARY_ADD_REPEAT, dictionary.getCode());
        }
        return super.updateSelective(dictionary);
    }

    @Override
    public int delete(String... strings) throws ApplicationException {
        if (ArrayUtils.isEmpty(strings)) {
            return 0;
        }
        for (String id : strings) {
            Dictionary paramDictionary = new Dictionary();
            paramDictionary.setParentId(id);
            // 根据parentId查询记录，如果存在，则存在子级，则不能删除
            if (super.exists(paramDictionary)) {
                throw new ApplicationException(DictionaryExceptionCode.DICTIONARY_EXISTS_CHILDREN_DELETE_EXCEPTION);
            }
        }
        return super.delete(strings);
    }
}
