package com.sky.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.model.Dictionary;
import com.sky.system.api.vo.DictionaryVO;

import java.util.List;

/**
 * @date 2020-11-03 003 10:00
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 保存
     *
     * @param dto dto
     * @return boolean
     */
    boolean save(DictionaryDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return boolean
     */
    boolean update(DictionaryDto dto);

    /**
     * 查询
     *
     * @param dto dto
     * @return DictionaryVO
     */
    List<DictionaryVO> listDictionary(DictionaryDto dto);

    /**
     * 查询
     *
     * @param dto dto
     * @return DictionaryVO
     */
    List<DictionaryVO> tree(DictionaryDto dto);

    /**
     * 物理删除
     *
     * @param id id
     * @return int
     */
    int physicalDeleteById(Long id);

    /**
     * 获取数据字典的值
     *
     * @param code code
     * @return String
     */
    String getValue(String code);
}
