package com.stream.garden.excel.service.impl;

import com.stream.garden.excel.dao.IExcelConfigDao;
import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.excel.service.IExcelConfigService;
import com.stream.garden.framework.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-11-04 16:22
 */
@Service
public class ExcelConfigService extends AbstractBaseService<ExcelConfig, String> implements IExcelConfigService {

    public ExcelConfigService(IExcelConfigDao iExcelConfigDao) {
        super(iExcelConfigDao);
    }
}
