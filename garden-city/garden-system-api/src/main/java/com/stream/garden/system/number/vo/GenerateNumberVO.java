package com.stream.garden.system.number.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangyuyuan
 * @date 2020-06-05 9:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateNumberVO {

    /**
     * 业务编码
     */
    private String code;

    /**
     * 生成流水号数量
     */
    private int num;
}
