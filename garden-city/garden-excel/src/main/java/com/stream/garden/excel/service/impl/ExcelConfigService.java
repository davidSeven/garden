package com.stream.garden.excel.service.impl;

import com.stream.garden.excel.dao.IExcelConfigDao;
import com.stream.garden.excel.exception.ExcelExceptionCode;
import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.excel.service.IExcelConfigService;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author garden
 * @date 2019-11-04 16:22
 */
@Service
public class ExcelConfigService extends AbstractBaseService<ExcelConfig, String> implements IExcelConfigService {

    public ExcelConfigService(IExcelConfigDao iExcelConfigDao) {
        super(iExcelConfigDao);
    }

    @Override
    public int insert(ExcelConfig excelConfig) throws ApplicationException {
        ExcelConfig paramExcelConfig = new ExcelConfig();
        paramExcelConfig.setCode(excelConfig.getCode());
        if (super.exists(paramExcelConfig)) {
            throw new ApplicationException(ExcelExceptionCode.EXCEL_CONFIG_CODE_REPEAT, excelConfig.getCode());
        }
        return super.insertSelective(excelConfig);
    }

    @Override
    public int update(ExcelConfig excelConfig) throws ApplicationException {
        ExcelConfig paramExcelConfig = new ExcelConfig();
        paramExcelConfig.setId(excelConfig.getId());
        paramExcelConfig.setCode(excelConfig.getCode());
        if (super.exists(paramExcelConfig)) {
            throw new ApplicationException(ExcelExceptionCode.EXCEL_CONFIG_CODE_REPEAT, excelConfig.getCode());
        }
        return super.updateSelective(excelConfig);
    }

    @Override
    public ExcelConfig getByCode(String code) throws ApplicationException {
        try {
            ExcelConfig paramExcelConfig = new ExcelConfig();
            paramExcelConfig.setCode(code);
            List<ExcelConfig> list = super.list(paramExcelConfig);
            if (CollectionUtil.isNotEmpty(list)) {
                return list.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("查询导入导出配置异常");
        }
        return null;
    }
}
