package com.sky.system.dao;

import com.sky.framework.dao.mapper.MyBaseMapper;
import com.sky.system.api.model.Dictionary;
import org.apache.ibatis.annotations.Param;

public interface DictionaryDao extends MyBaseMapper<Dictionary> {

    int increaseChildrenSize(@Param("id") Long id);

    int decreaseChildrenSize(@Param("id") Long id);
}
