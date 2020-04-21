package com.stream.garden.system.function.vo;

import com.stream.garden.framework.api.vo.BasePageVO;
import com.stream.garden.system.function.model.FunctionFieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2020-04-08 19:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionFieldTypeVO extends BasePageVO<FunctionFieldType> {
    private static final long serialVersionUID = -5297522303854402866L;
}
