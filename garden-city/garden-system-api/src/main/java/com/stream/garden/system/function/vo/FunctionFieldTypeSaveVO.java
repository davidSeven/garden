package com.stream.garden.system.function.vo;

import com.stream.garden.framework.api.vo.BasePageVO;
import com.stream.garden.system.function.model.FunctionFieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author garden
 * @date 2020-04-08 19:12
 */
@Data
public class FunctionFieldTypeSaveVO {

    private String functionId;
    private Integer type;

    private List<FunctionFieldType> list;
}
