package com.stream.garden.system.function.vo;

import com.stream.garden.framework.api.vo.BasePageVO;
import com.stream.garden.system.function.model.FunctionField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2020-01-22 11:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionFieldVO extends BasePageVO<FunctionField, String> {
    private static final long serialVersionUID = -7061520847521702363L;
}
