package com.stream.garden.i18n.service.impl;

import com.stream.garden.excel.model.ExcelParseResult;
import com.stream.garden.excel.service.impl.AbstractExcelImportService;
import com.stream.garden.framework.cache.util.JsonUtil;
import com.stream.garden.i18n.model.I18n;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author garden
 * @date 2019-11-09 11:39
 */
@Service("I18nExcelImportService")
public class I18nExcelImportService extends AbstractExcelImportService {

    @Override
    protected LinkedHashMap<String, Object> createDataMap() {
        LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>();
        List<I18n> i18nList = new ArrayList<>();
        dataMap.put("i18nList", i18nList);
        return dataMap;
    }

    @Override
    public ExcelParseResult parseExcel(LinkedHashMap<String, Object> dataMap) {
        logger.debug("解析得到的数据");
        logger.debug(JsonUtil.objectToJson(dataMap));
        ExcelParseResult result = new ExcelParseResult();
        result.setStatus(true);
        return result;
    }

    @Override
    public void afterSuccess(LinkedHashMap<String, Object> dataMap) {
        logger.debug(JsonUtil.objectToJson(dataMap));
    }
}
