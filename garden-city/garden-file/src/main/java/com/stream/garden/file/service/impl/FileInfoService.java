package com.stream.garden.file.service.impl;

import com.stream.garden.file.config.FileConfig;
import com.stream.garden.file.dao.IFileInfoDao;
import com.stream.garden.file.exception.FileExceptionCode;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.file.util.FileUtil;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.service.AbstractBaseService;
import com.stream.garden.framework.util.CollectionUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

/**
 * @author garden
 * @date 2019-09-26 15:37
 */
@Service
public class FileInfoService extends AbstractBaseService<FileInfo, IFileInfoDao> implements IFileInfoService {

    @Autowired
    private FileConfig fileConfig;

    @Override
    public List<FileInfo> update(List<FileInfo> files) throws ApplicationException {
        if (!CollectionUtils.isEmpty(files)) {
            super.insertBatch(files);
        }
        return files;
    }

    @Override
    public FileInfo getFileInfo(String bizCode, String bizId) throws ApplicationException {
        try {
            return this.getMapper().getFileInfo(bizCode, bizId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @Override
    public int deleteByBiz(FileInfo fileInfo) throws ApplicationException {
        try {
            FileInfo paramFileInfo = new FileInfo();
            paramFileInfo.setBizCode(fileInfo.getBizCode());
            paramFileInfo.setBizId(fileInfo.getBizId());
            List<FileInfo> fileInfoList = super.list(paramFileInfo);
            if (CollectionUtil.isNotEmpty(fileInfoList)) {
                String[] ids = fileInfoList.stream().map(FileInfo::getId).toArray(String[]::new);
                return super.delete(ids);
            }
            return 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @Override
    public ByteArrayInputStream getFileInfoStream(String bizCode, String bizId) throws ApplicationException {
        try {
            FileInfo fileInfo = this.getFileInfo(bizCode, bizId);
            if (null == fileInfo) {
                throw new ApplicationException("???????????????????????????");
            }
            File file = new File(fileInfo.getPhysicalPath());
            if (!file.exists()) {
                throw new ApplicationException("???????????????");
            }
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @Override
    public List<FileInfo> uploadFiles(FileParameter fileParameter) throws ApplicationException {
        try {
            // ??????????????????????????????
            fileParameter.setFileRootPath(fileConfig.getUploadPath());
            // ????????????
            List<FileInfo> files = FileUtil.uploadLocal(fileParameter);
            // ??????????????????
            return this.update(files);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(FileExceptionCode.FILE_INFO_UPLOAD_FILE_FAIL);
        }
    }

    @Override
    public FileInfo uploadFile(FileParameter fileParameter) throws ApplicationException {
        try {
            // ?????????????????????
            List<FileInfo> list = this.uploadFiles(fileParameter);
            if (CollectionUtil.isNotEmpty(list)) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(FileExceptionCode.FILE_INFO_UPLOAD_FILE_FAIL);
        }
    }
}
