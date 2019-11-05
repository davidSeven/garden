package com.stream.garden.excel.service.impl;

import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.excel.service.IExcelConfigService;
import com.stream.garden.excel.util.JxlsUtils;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.framework.api.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;

/**
 * @author garden
 * @date 2019-11-05 11:33
 */
public abstract class AbstractExcelImportService {
    protected Logger logger = LoggerFactory.getLogger(AbstractExcelImportService.class);

    @Autowired
    private IExcelConfigService excelConfigService;

    @Autowired
    private IFileInfoService fileInfoService;

    /**
     * 导入excel
     *
     * @param excelId    导入的excel的文件id
     * @param excelCode  导入的excel的文件code
     * @param configCode 导入导出管理中配置的code
     * @return boolean
     */
    public boolean importExcel(String excelId, String excelCode, String configCode) throws ApplicationException {
        // 查询导入配置信息
        ExcelConfig excelConfig = this.excelConfigService.getByCode(configCode);
        if (null == excelConfig) {
            throw new ApplicationException("导入excel配置信息为空");
        }
        // 查询解析模板信息
        if (StringUtils.isEmpty(excelConfig.getImportCode()) || StringUtils.isEmpty(excelConfig.getImportId())) {
            throw new ApplicationException("未配置导入模板");
        }
        ByteArrayInputStream importFileInfo = this.fileInfoService.getFileInfoStream(excelConfig.getImportCode(), excelConfig.getImportId());
        if (null == importFileInfo) {
            throw new ApplicationException("配置的导入模板不存在");
        }
        // 查询上传的excel文件
        ByteArrayInputStream excelFileInfo = this.fileInfoService.getFileInfoStream(excelCode, excelId);
        if (null == excelFileInfo) {
            throw new ApplicationException("上传的文件不存在");
        }

        LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>();
        try {
            JxlsUtils.importExcle(importFileInfo, excelFileInfo, dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("解析excel异常");
        }

        this.parseExcel(dataMap);

        try {
            this.afterSuccess(dataMap);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("导入excel异常");
        }

        return false;
    }

    public abstract boolean parseExcel(LinkedHashMap<String, Object> dataMap);

    public abstract void afterSuccess(LinkedHashMap<String, Object> dataMap);
}
