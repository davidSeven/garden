package com.stream.garden.i18n.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.i18n.model.I18n;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 * @date 2019-10-24 17:13
 */
@Mapper
public interface II18nDao extends IBaseMapper<I18n> {
}
