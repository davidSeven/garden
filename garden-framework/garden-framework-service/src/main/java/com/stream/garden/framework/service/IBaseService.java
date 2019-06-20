package com.stream.garden.framework.service;

import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.vo.BasePageVO;

import java.util.List;

public interface IBaseService<T, ID> {

    public int insert(T t);

    public int insertSelective(T t);

    public int insertBatch(List<T> list);

    public int update(T t);

    public int updateSelective(T t);

    public int delete(ID... ids);

    public int disable(ID... ids);

    public int enable(ID... ids);

    public T get(ID id);

    public List<T> getByIds(ID... ids);

    public List<T> list(T t);

    public PageInfo<T> pageList(BasePageVO<T, ID> pageVO);
}
