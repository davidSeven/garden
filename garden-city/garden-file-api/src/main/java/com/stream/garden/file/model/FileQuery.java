package com.stream.garden.file.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author garden
 * @date 2019-09-25 14:45
 */
@Data
public class FileQuery implements Serializable {
    private static final long serialVersionUID = 5495371264074060844L;

    /**
     * 文件地址
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String[] fileNames;
}
