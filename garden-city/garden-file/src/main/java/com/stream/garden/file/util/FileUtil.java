package com.stream.garden.file.util;

import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author garden
 * @date 2019-09-25 14:52
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

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
}
