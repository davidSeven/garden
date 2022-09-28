package com.sky.base.api.dto;

import com.sky.framework.validator.groups.Delete;
import com.sky.framework.validator.groups.Save;
import com.sky.framework.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel(value = "CustomerDto", description = "CustomerDto信息")
public class CustomerDto {

    @NotNull(message = "ID不能为空", groups = {Update.class, Delete.class})
    @ApiModelProperty(value = "ID")
    private Long id;

    @Size(message = "备注不能超过255个字符", max = 255, groups = {Save.class, Update.class})
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @Size(message = "客户名称不能超过100个字符", max = 100, groups = {Save.class, Update.class})
    @NotBlank(message = "客户名称不能为空", groups = {Save.class, Update.class})
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @NotBlank(message = "状态不能为空", groups = {Save.class, Update.class})
    @ApiModelProperty(value = "状态")
    private String state;

    @Size(message = "邮箱不能超过64个字符", max = 64, groups = {Save.class, Update.class})
    @Email(message = "邮箱格式不正确", groups = {Save.class, Update.class})
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Size(message = "手机不能超过32个字符", max = 32, groups = {Save.class, Update.class})
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    @ApiModelProperty(value = "客户编码集合")
    private List<String> customerCodeList;

}
