package com.stream.garden.system.menu.dao;

import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.system.menu.model.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author garden
 */
@Mapper
public interface IMenuDao extends IBaseMapper<Menu, String> {
}
