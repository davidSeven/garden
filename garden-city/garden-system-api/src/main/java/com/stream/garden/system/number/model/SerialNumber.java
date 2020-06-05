package com.stream.garden.system.number.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2020-06-04 17:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SerialNumber extends BaseModel<Long> {
    private static final long serialVersionUID = 6436332194639283835L;

    /**
     * 业务编码
     */
    private String code;

    /**
     * 当前序列
     */
    private Long currentSequence;

    /**
     * 步长
     */
    private Integer step;

    /**
     * 流水号长度
     */
    private Integer length;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 流水号类型
     */
    private Integer type;

    /**
     * 模板
     */
    private String template;

    /**
     * 周期格式
     */
    private String cycleFormat;

    /**
     * 当前周期
     */
    private String currentCycle;

    /**
     * 缓存数量
     */
    private Integer cacheSize;

    /**
     * 转换器
     */
    private String converts;
}
