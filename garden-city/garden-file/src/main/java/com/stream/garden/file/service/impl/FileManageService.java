package com.stream.garden.file.service.impl;

import com.stream.garden.file.dao.IFileManageDao;
import com.stream.garden.file.model.FileManage;
import com.stream.garden.file.service.IFileManageService;
import com.stream.garden.framework.service.AbstractBaseService;
import org.springframework.stereotype.Service;

/**
 * @author garden
 * @date 2019-09-26 14:31
 */
@Service
public class FileManageService extends AbstractBaseService<FileManage, String> implements IFileManageService {

    public FileManageService(IFileManageDao iFileManageDao) {
        super(iFileManageDao);
    }

}
