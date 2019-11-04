package com.stream.garden.excel.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-11-04 16:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExcelConfig extends BaseModel<String> {
    private static final long serialVersionUID = 2593343486325433644L;

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 导入文件编码
     */
    private String importCode;
    /**
     * 导入文件ID
     */
    private String importId;
    /**
     * 导入文件名称
     */
    private String importName;
    /**
     * 导出文件编码
     */
    private String exportCode;
    /**
     * 导出文件ID
     */
    private String exportId;
    /**
     * 导出文件名称
     */
    private String exportName;
    /**
     * 状态
     */
    private String state;

}
