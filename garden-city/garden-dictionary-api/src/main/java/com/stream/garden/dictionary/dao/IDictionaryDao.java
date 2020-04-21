package com.stream.garden.dictionary.dao;

import com.stream.garden.dictionary.model.Dictionary;
import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-09-29 13:36
 */
@Mapper
public interface IDictionaryDao extends IBaseMapper<Dictionary> {
}
