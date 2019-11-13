package com.stream.garden.file.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-09-26 11:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileManage extends BaseModel<String> {
    private static final long serialVersionUID = 8428540599737122340L;

    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 状态
     */
    private String state;

    /**
     * 存储类型
     */
    private String storageType;
}
