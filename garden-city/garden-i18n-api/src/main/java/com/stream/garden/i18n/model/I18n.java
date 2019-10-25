package com.stream.garden.i18n.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-10-24 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class I18n extends BaseModel<String> {
    private static final long serialVersionUID = 1046836225524045084L;

    /**
     * 国际化Code
     */
    private String code;
    /**
     * 国际化值
     */
    private String value;
    /**
     * 语言类型
     */
    private String languageType;
    /**
     * 状态
     */
    private String state;
}
