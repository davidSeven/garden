package com.stream.garden.excel.service.impl;

import com.stream.garden.excel.model.ExcelConfig;
import com.stream.garden.excel.model.ExcelParseResult;
import com.stream.garden.excel.model.RowResult;
import com.stream.garden.excel.service.IExcelConfigService;
import com.stream.garden.excel.util.JxlsUtils;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.util.CollectionUtil;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        // 创建业务解析map
        LinkedHashMap<String, Object> dataMap = this.createDataMap();
        try {
            JxlsUtils.importExcle(importFileInfo, excelFileInfo, dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("解析excel异常");
        }

        // 业务处理
        ExcelParseResult excelParseResult = this.parseExcel(dataMap);

        // 判断有没有错误信息
        if (CollectionUtil.isNotEmpty(excelParseResult.getRowResults())) {
            ByteArrayOutputStream os;
            try {
                os = updateExcelFile(excelFileInfo, excelParseResult.getRowResults());
                uploadErrorFile(os, excelId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new ApplicationException("上传错误信息异常");
            }
        }

        try {
            // 所有验证成功之后
            if (excelParseResult.isStatus()) {
                this.afterSuccess(dataMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException("导入excel异常");
        }

        return false;
    }

    /**
     * 错误信息增加在excel的最后一样上
     *
     * @param is         is
     * @param rowResults rowResults
     * @return os
     * @throws Exception e
     */
    private ByteArrayOutputStream updateExcelFile(InputStream is, List<RowResult> rowResults) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Workbook wb = null;
        WritableWorkbook book = null;
        try {
            wb = Workbook.getWorkbook(is);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setWriteAccess(null);
            book = Workbook.createWorkbook(bos, wb, wbSettings);
            WritableSheet sheet = book.getSheet(0);
            // 获取当前列数
            int cols = sheet.getColumns();
            Map<Integer, String> rowMsgsMap = new HashMap<>();
            // 拼接行错误信息
            for (RowResult rowResult : rowResults) {
                int row = rowResult.getRowIndex();
                if (rowMsgsMap.containsKey(row)) {
                    rowMsgsMap.put(row, rowMsgsMap.get(row) + rowResult.getErrorMessage() + "; ");
                } else {
                    rowMsgsMap.put(row, rowResult.getErrorMessage() + "; ");
                }
            }
            // 写入行错误信息
            for (Map.Entry<Integer, String> entry : rowMsgsMap.entrySet()) {
                sheet.addCell(new Label(cols, entry.getKey(), entry.getValue()));
            }
            book.write();
        } finally {
            if (book != null) {
                book.close();
            }
            if (wb != null) {
                wb.close();
            }
        }
        bos.close();
        return bos;
    }

    /**
     * 上传Excel错误文件
     *
     * @param os      os
     * @param excelId excelId
     */
    private void uploadErrorFile(ByteArrayOutputStream os, String excelId) {
        FileInfo file = new FileInfo();
        file.setOriginalName("temp_error.xls");
        file.setExtendName("xls");
        file.setName("temp_error");
        file.setBytes(os.toByteArray());
        file.setSize((long) os.size());
        file.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        FileInfo[] files = new FileInfo[]{file};
        FileParameter fileParameter = new FileParameter();
        fileParameter.setFileManageId(excelId);
        fileParameter.setBizCode("upload_error_xls");
        fileParameter.setBizCode(excelId);
        fileParameter.setFiles(files);
        try {
            this.fileInfoService.uploadFile(fileParameter);
        } catch (ApplicationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 创建业务数据
     *
     * @return 业务数据
     */
    protected abstract LinkedHashMap<String, Object> createDataMap();

    /**
     * 业务逻辑
     *
     * @param dataMap 数据对象
     * @return 业务处理结果
     */
    public abstract ExcelParseResult parseExcel(LinkedHashMap<String, Object> dataMap);

    /**
     * 验证成功之后的操作
     *
     * @param dataMap 数据对象
     */
    public abstract void afterSuccess(LinkedHashMap<String, Object> dataMap);
}
