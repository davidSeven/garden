package com.stream.garden.framework.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.vo.BasePageVO;

import java.util.List;

/**
 * @author garden
 * @param <T> 实体对象
 * @param <ID> id类型
 */
public interface IBaseService<T, ID> {

    /**
     * insert
     * @param t t
     * @return int
     * @throws ApplicationException exception
     */
    public int insert(T t) throws ApplicationException;

    public int insertSelective(T t) throws ApplicationException;

    public int insertBatch(List<T> list) throws ApplicationException;

    public int update(T t) throws ApplicationException;

    public int updateSelective(T t) throws ApplicationException;

    /**
     * delete
     * @param ids id
     * @return int
     * @throws ApplicationException exception
     */
    public int delete(ID... ids) throws ApplicationException;

    public int disable(ID... ids) throws ApplicationException;

    public int enable(ID... ids) throws ApplicationException;

    public T get(ID id) throws ApplicationException;

    public List<T> getByIds(ID... ids) throws ApplicationException;

    public List<T> list(T t) throws ApplicationException;

    /**
     * pageList
     * @param pageVO pageVO
     * @return pageInfo
     * @throws ApplicationException exception
     */
    public PageInfo<T> pageList(BasePageVO<T, ID> pageVO) throws ApplicationException;

    public boolean exists(T t) throws ApplicationException;
}
