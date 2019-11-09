package com.stream.garden.file.service;

import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author garden
 * @date 2019-09-26 14:20
 */
public interface IFileInfoService extends IBaseService<FileInfo, String> {


    List<FileInfo> update(List<FileInfo> files) throws ApplicationException;

    FileInfo getFileInfo(String bizCode, String bizId) throws ApplicationException;

    int deleteByBiz(FileInfo fileInfo) throws ApplicationException;

    /**
     * 获取文件流
     *
     * @param bizCode 业务编码
     * @param bizId   业务id
     * @return 文件流
     * @throws ApplicationException e
     */
    ByteArrayInputStream getFileInfoStream(String bizCode, String bizId) throws ApplicationException;

    /**
     * 上传文件
     *
     * @param fileParameter 文件
     * @return 结果
     * @throws ApplicationException e
     */
    List<FileInfo> uploadFiles(FileParameter fileParameter) throws ApplicationException;

    /**
     * 上传文件
     *
     * @param fileParameter 文件
     * @return 结果
     * @throws ApplicationException e
     */
    FileInfo uploadFile(FileParameter fileParameter) throws ApplicationException;
}
