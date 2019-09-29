package com.stream.garden.dictionary.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-09-29 13:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dictionary extends BaseModel<String> {
    private static final long serialVersionUID = 3335195271151010136L;

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 父级编码
     */
    private String parentCode;
    /**
     * 状态
     */
    private String state;
    /**
     * 顺序
     */
    private Integer sorts;
    /**
     * 值
     */
    private String value;
}
