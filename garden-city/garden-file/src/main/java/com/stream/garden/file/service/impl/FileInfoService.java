package com.stream.garden.file.service.impl;

import com.stream.garden.file.dao.IFileInfoDao;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.service.AbstractBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author garden
 * @date 2019-09-26 15:37
 */
@Service
public class FileInfoService extends AbstractBaseService<FileInfo, String> implements IFileInfoService {

    public FileInfoService(IFileInfoDao iFileInfoDao) {
        super(iFileInfoDao);
    }

    private IFileInfoDao getDao() {
        return (IFileInfoDao) super.baseMapper;
    }

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
            return this.getDao().getFileInfo(bizCode, bizId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }
}
