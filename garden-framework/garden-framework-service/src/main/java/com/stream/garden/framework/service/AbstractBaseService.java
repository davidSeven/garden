package com.stream.garden.framework.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.PageSize;
import com.stream.garden.framework.api.vo.BasePageVO;
import com.stream.garden.framework.jdbc.mapper.IBaseMapper;
import com.stream.garden.framework.util.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author garden
 * @param <T> 实体对象
 * @param <ID> id类型
 */
@Transactional(rollbackFor = Exception.class)
public abstract class AbstractBaseService<T, ID> implements IBaseService<T, ID> {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractBaseService.class);
    protected IBaseMapper<T, ID> baseMapper;

    public AbstractBaseService(IBaseMapper<T, ID> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public int insert(T t) throws ApplicationException {
        setDefault(t, true);
        return baseMapper.insert(t);
    }

    @Override
    public int insertSelective(T t) throws ApplicationException {
        setDefault(t, true);
        return baseMapper.insertSelective(t);
    }

    @Override
    public int insertBatch(List<T> list) throws ApplicationException {
        if (CollectionUtil.isNotEmpty(list)) {
            for (T t : list) {
                setDefault(t, true);
            }
        }
        return baseMapper.insertBatch(list);
    }

    @Override
    public int update(T t) throws ApplicationException {
        setDefault(t, false);
        return baseMapper.update(t);
    }

    @Override
    public int updateSelective(T t) throws ApplicationException {
        setDefault(t, false);
        return baseMapper.updateSelective(t);
    }

    @Override
    public int delete(ID... ids) throws ApplicationException {
        return baseMapper.delete(ids);
    }

    @Override
    public int disable(ID... ids) throws ApplicationException {
        return baseMapper.disable(ids);
    }

    @Override
    public int enable(ID... ids) throws ApplicationException {
        return baseMapper.enable(ids);
    }

    @Override
    public T get(ID id) throws ApplicationException {
        return baseMapper.get(id);
    }

    @Override
    public List<T> getByIds(ID... ids) throws ApplicationException {
        return baseMapper.getByIds(ids);
    }

    @Override
    public List<T> list(T t) throws ApplicationException {
        return baseMapper.list(t);
    }

    /**
     * 分页信息
     *
     * @param pageVO 分页信息
     * @return 返回数据
     */
    @Override
    public PageInfo<T> pageList(BasePageVO<T, ID> pageVO) throws ApplicationException {
        // 设置PageHelper参数信息
        PageSize pageSize = pageVO.getPageSize();
        T t = pageVO.getData();
        PageHelper.startPage(pageSize.getPage(), pageSize.getPageSize(), pageSize.isCount());

        // 查询分页信息
        Page<T> page = baseMapper.pageList(t);

        // 封装分页数据
        PageInfo<T> pageInfo = new PageInfo<>(pageSize);
        pageInfo.setRows(page);
        pageInfo.setRowTotal((int) page.getTotal());
        pageInfo.setPageTotal(page.getPages());

        return pageInfo;
    }

    @Override
    public boolean exists(T t) throws ApplicationException {
        int exists = baseMapper.exists(t);
        return exists != 0;
    }

    /**
     * 设置默认值
     *
     * @param t        对象
     * @param isInsert 是否新增
     */
    private void setDefault(T t, boolean isInsert) {

    }
}
