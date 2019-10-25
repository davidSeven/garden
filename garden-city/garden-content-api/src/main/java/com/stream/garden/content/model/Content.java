package com.stream.garden.content.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-10-25 16:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Content extends BaseModel<String> {
    private static final long serialVersionUID = 4381583298346990326L;

    private String code;
    private String url;
    private String content;
    private String state;
}
