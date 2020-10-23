package com.forest.framework.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @date 2020-09-22 022 14:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseModel<T extends Model<?>, ID> extends Model<T> implements Serializable {
    private static final long serialVersionUID = 442674058424324628L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private ID id;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private Timestamp creationDate;

    @ApiModelProperty("修改人")
    private String updatedBy;

    @ApiModelProperty("修改时间")
    private Timestamp updatedDate;

    @ApiModelProperty("是否禁用")
    private Integer enabledFlag;

    @ApiModelProperty("日志ID")
    private String traceId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
