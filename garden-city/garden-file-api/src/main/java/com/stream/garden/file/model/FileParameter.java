package com.stream.garden.file.model;

import lombok.Data;

/**
 * @author garden
 * @date 2019-09-26 15:44
 */
@Data
public class FileParameter {

    /**
     * 业务代码
     */
    private String bizCode;
    /**
     * 业务ID
     */
    private String bizId;
    /**
     * 上传文件数量
     */
    private int num;
    /**
     * 上传的文件
     */
    private FileInfo[] files;
}
