package com.stream.garden.framework.service;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.vo.BasePageVO;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> 实体对象
 * @author garden
 */
public interface IBaseService<T> {

    /**
     * insert
     *
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
     *
     * @param ids id
     * @return int
     * @throws ApplicationException exception
     */
    public int delete(Serializable... ids) throws ApplicationException;

    public int disable(Serializable... ids) throws ApplicationException;

    public int enable(Serializable... ids) throws ApplicationException;

    public T get(Serializable id) throws ApplicationException;

    public List<T> getByIds(Serializable... ids) throws ApplicationException;

    public List<T> list(T t) throws ApplicationException;

    /**
     * pageList
     *
     * @param pageVO pageVO
     * @return pageInfo
     * @throws ApplicationException exception
     */
    public PageInfo<T> pageList(BasePageVO<T> pageVO) throws ApplicationException;

    public boolean exists(T t) throws ApplicationException;
}
