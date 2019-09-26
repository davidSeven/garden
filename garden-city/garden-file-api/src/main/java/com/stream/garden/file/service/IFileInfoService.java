package com.stream.garden.file.service;

import com.stream.garden.file.model.FileInfo;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.service.IBaseService;

import java.util.List;

/**
 * @author garden
 * @date 2019-09-26 14:20
 */
public interface IFileInfoService extends IBaseService<FileInfo, String> {


    List<FileInfo> update(List<FileInfo> files) throws ApplicationException;

    FileInfo getFileInfo(String bizCode, String bizId) throws ApplicationException;
}
