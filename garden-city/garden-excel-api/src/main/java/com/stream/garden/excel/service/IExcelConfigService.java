package com.stream.garden.excel.service;

import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;

/**
 * @author garden
 * @date 2019-11-04 16:08
 */
public interface IExcelConfigService extends IBaseService<ExcelConfig> {

    /**
     * 根据code查询配置信息
     *
     * @param code code
     * @return excelConfig
     */
    ExcelConfig getByCode(String code) throws ApplicationException;
}
