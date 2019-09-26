package com.stream.garden.file.model;

import com.stream.garden.framework.api.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author garden
 * @date 2019-09-26 11:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileInfo extends BaseModel<String> {
    private static final long serialVersionUID = 7264611502342278404L;

    /**
     * 文件所属ID
     */
    private String fileManageId;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 业务编码
     */
    private String bizCode;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 原始文件名称，包含扩展名
     */
    private String originalName;
    /**
     * 文件扩展名，不包含.
     */
    private String extendName;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 存储物理路径
     */
    private String physicalPath;
    /**
     * 访问路径
     */
    private String visitPath;
    /**
     * 文件大小（单件byte）
     */
    private int size;
    /**
     * 文件头类型
     */
    private String contentType;
}
