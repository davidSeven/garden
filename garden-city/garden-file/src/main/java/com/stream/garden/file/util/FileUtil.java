package com.stream.garden.file.util;

import com.stream.garden.file.constants.StorageConfig;
import com.stream.garden.file.exception.FileExceptionCode;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.util.CollectionUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.*;

/**
 * 文件工具类
 *
 * @author garden
 * @date 2019-09-25 14:52
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final String MAP_KEY_ADD = "add";

    private FileUtil() {
    }

    public static FileParameter convertFileParameter(MultipartHttpServletRequest multipartHttpServletRequest) {
        FileParameter fileParameter = new FileParameter();
        fileParameter.setBizCode(multipartHttpServletRequest.getParameter("bizCode"));
        fileParameter.setBizId(multipartHttpServletRequest.getParameter("bizId"));
        fileParameter.setFiles(convert(multipartHttpServletRequest));
        return fileParameter;
    }

    /**
     * 转换文件
     *
     * @param multipartHttpServletRequest request
     * @return file
     */
    private static FileInfo[] convert(MultipartHttpServletRequest multipartHttpServletRequest) {
        List<FileInfo> files = new ArrayList<>();
        List<MultipartFile> uploadFiles = multipartHttpServletRequest.getFiles("file");
        String bizCode = multipartHttpServletRequest.getParameter("bizCode");
        String bizId = multipartHttpServletRequest.getParameter("bizId");
        String[] fileIds = multipartHttpServletRequest.getParameterValues("id");
        String[] types = multipartHttpServletRequest.getParameterValues("type");
        String[] names = multipartHttpServletRequest.getParameterValues("name");
        String[] remarks = multipartHttpServletRequest.getParameterValues("remark");
        if (uploadFiles != null) {
            for (int i = 0, len = uploadFiles.size(); i < len; i++) {
                MultipartFile multipartFile = uploadFiles.get(i);
                FileInfo file = new FileInfo();
                file.setBizCode(bizCode);
                file.setBizId(bizId);
                if (fileIds != null && fileIds.length > i) {
                    if (fileIds[i] != null && fileIds[i].length() > 0) {
                        file.setId(fileIds[i].trim());
                    }
                }
                if (!multipartFile.isEmpty() || file.getId() != null) {
                    if (types != null && types.length > i) {
                        file.setType(types[i]);
                    }
                    if (names != null && names.length > i) {
                        file.setName(names[i]);
                    }
                    if (remarks != null && remarks.length > i) {
                        file.setRemark(remarks[i]);
                    }
                    if (!multipartFile.isEmpty()) {
                        file.setOriginalName(multipartFile.getOriginalFilename());
                        file.setContentType(multipartFile.getContentType());
                        file.setSize(multipartFile.getSize());

                        try {
                            file.setBytes(multipartFile.getBytes());
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                    if (file.getOriginalName() != null) {
                        int pos = file.getOriginalName().lastIndexOf(".");
                        String tempName = file.getOriginalName();
                        if (pos != -1) {
                            file.setExtendName(file.getOriginalName().substring(pos + 1));
                            tempName = file.getOriginalName().substring(0, pos);
                        }
                        if (file.getName() == null || file.getName().trim().length() == 0) {
                            file.setName(tempName);
                        }
                    }
                    if (file.getDisplayIndex() == null) {
                        file.setDisplayIndex(10 * (i + 1));
                    }
                    files.add(file);
                }
            }
        }
        return files.toArray(new FileInfo[0]);
    }

    /**
     * 上传文件到本地
     *
     * @param fileParameter 文件参数
     * @return 文件列表
     * @throws ApplicationException e
     */
    public static List<FileInfo> uploadLocal(FileParameter fileParameter) throws ApplicationException {
        if (null == fileParameter.getStorageType()) {
            fileParameter.setStorageType(StorageConfig.FILE.name());
        }
        Map<String, List<FileInfo>> fileMap = null;
        if (StorageConfig.FILE.name().equals(fileParameter.getStorageType())) {
            fileMap = localParse(fileParameter);
        } else if (StorageConfig.FAST_DFS.name().equals(fileParameter.getStorageType())) {

        }
        if (null == fileMap) {
            throw new ApplicationException(FileExceptionCode.FILE_INFO_UPLOAD_FILE_FAIL);
        }
        List<FileInfo> newFiles = fileMap.get(MAP_KEY_ADD);
        if (CollectionUtil.isNotEmpty(newFiles)) {
            for (FileInfo file : newFiles) {
                // 清除文件字节信息
                file.setBytes(null);
            }
            return newFiles;
        }
        return new ArrayList<>();
    }

    private static Map<String, List<FileInfo>> localParse(FileParameter fileParameter) throws ApplicationException {
        return localParse(fileParameter.getFileRootPath(), fileParameter.getBizCode(), fileParameter.getBizId(), fileParameter.getFileManageId(), fileParameter.getFiles());
    }

    private static Map<String, List<FileInfo>> localParse(String fileRootPath, String bizCode, String bizId, String fileManageId, FileInfo... files) throws ApplicationException {
        Map<String, List<FileInfo>> fileMap = new HashMap<>();
        if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(bizId)) {
            return fileMap;
        }
        if (ArrayUtils.isEmpty(files)) {
            return fileMap;
        }
        List<FileInfo> addFiles = new ArrayList<>();
        fileMap.put(MAP_KEY_ADD, addFiles);
        for (int i = 0, len = files.length; i < len; i++) {
            FileInfo file = files[i];
            if (file == null) {
                continue;
            }
            file.setFileManageId(fileManageId);
            file.setBizCode(bizCode);
            file.setBizId(bizId);
            file.setDisplayIndex(10 * (i + 1));
            file.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            addFiles.add(file);
            if (file.getBytes() != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(File.separatorChar)
                        .append(UUID.randomUUID().toString().replaceAll("-", ""));
                BufferedOutputStream stream = null;
                try {
                    mkdirs(fileRootPath + fileName.toString());
                    fileName.append(File.separatorChar)
                            .append(file.getId())
                            .append(".")
                            .append(file.getExtendName());
                    stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(fileRootPath + fileName.toString())));
                    stream.write(file.getBytes());
                } catch (Exception e) {
                    logger.error("上传文件失败！[" + file.getBizCode() + ", " + file.getBizId() + ", " + file.getName() + "]", e);
                    throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
                } finally {
                    close(stream);
                }
                file.setPhysicalPath(fileRootPath + fileName.toString());
                file.setVisitPath(fileName.toString());
            }
        }
        return fileMap;
    }

    /**
     * 创建目录
     *
     * @param dirPath 目录路径
     */
    private static void mkdirs(String dirPath) {
        java.io.File dir = new java.io.File(dirPath);
        if (!dir.exists() && !dir.mkdirs()) {
            logger.error("创建目录失败：{}", dirPath);
        }
    }

    /**
     * 关闭文件流
     *
     * @param stream 文件流
     */
    private static void close(OutputStream stream) {
        if (null != stream) {
            try {
                stream.close();
            } catch (IOException e) {
                logger.error("文件流关闭错误", e);
            }
        }
    }
}
