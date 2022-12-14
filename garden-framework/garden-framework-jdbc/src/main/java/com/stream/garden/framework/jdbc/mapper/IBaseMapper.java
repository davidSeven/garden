package com.stream.garden.framework.jdbc.mapper;

import com.github.pagehelper.Page;
import com.stream.garden.framework.api.vo.BasePageVO;

import java.io.Serializable;
import java.util.List;

/**
 * mapper 父类
 *
 * @param <T>
 * @author
 */
public interface IBaseMapper<T> {

    /**
     * 新增
     *
     * @param t 对象
     * @return 影响条数
     */
    public int insert(T t);

    public int insertSelective(T t);

    /**
     * 批量新增
     *
     * @param list 集合对象
     * @return 影响条数
     */
    public int insertBatch(List<T> list);

    /**
     * 修改
     *
     * @param t 对象
     * @return 影响条数
     */
    public int update(T t);

    public int updateSelective(T t);

    /**
     * 删除
     *
     * @param ids id
     * @return 影响条数
     */
    public int delete(Serializable... ids);

    public int disable(Serializable... ids);

    public int enable(Serializable... ids);

    /**
     * 根据id查询
     *
     * @param id id
     * @return 对象
     */
    public T get(Serializable id);

    /**
     * 多个id查询
     *
     * @param ids id
     * @return 对象集合
     */
    public List<T> getByIds(Serializable... ids);

    /**
     * 对象查询
     *
     * @param t 对象
     * @return 对象集合
     */
    public List<T> list(T t);

    /**
     * 对象查询
     *
     * @param vo 对象
     * @return 对象集合
     */
    public Page<T> pageList(BasePageVO<T> vo);

    /**
     * 检查是否存在
     *
     * @param t 对象
     * @return 影响条数
     */
    public int exists(T t);
}
