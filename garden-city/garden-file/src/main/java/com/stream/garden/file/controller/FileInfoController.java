package com.stream.garden.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.file.util.FileUtil;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author garden
 * @date 2019-09-26 15:38
 */
@Controller
@RequestMapping("/file/fileInfo")
public class FileInfoController {
    private static final String MAP_KEY_ADD = "add";
    private Logger logger = LoggerFactory.getLogger(FileInfoController.class);

    @Autowired
    private IFileInfoService fileInfoService;

    private String uploadPath = "/www/garden";

    @RequestMapping(value = "/download/{bizCode}/{bizId}")
    public void download(@PathVariable("bizCode") String bizCode, @PathVariable("bizId") String bizId, HttpServletResponse response) {
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
            response.reset();

            if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(bizId)) {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }

            FileInfo fileInfo = fileInfoService.getFileInfo(bizCode, bizId);
            if (null == fileInfo) {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }

            File downFile = new File(fileInfo.getPhysicalPath());
            if (downFile.exists()) {
                byte[] downFileData = FileUtils.readFileToByteArray(downFile);
                if (null != downFileData && downFileData.length > 2) {
                    String resultFileName = fileInfo.getOriginalName();
                    response.setContentType("application/x-download");
                    response.addHeader("Content-Disposition", "attachment;filename=" + resultFileName);
                    response.addHeader("Content-Length", "" + downFileData.length);
                    stream.write(downFileData);
                }
            } else {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }
        } catch (Exception e) {
            logger.error("操作异常：", e);
            response.setContentType("application/json; charset=UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            Result<String> result = new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
            try {
                response.getWriter().append(JSONObject.toJSONString(result));
            } catch (Exception e2) {
                logger.error(e2.getMessage(), e2);
            }
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    stream.flush();
                } catch (IOException e) {
                    logger.error("文件流关闭错误：", e);
                }
            }
        }
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public Result<List<FileInfo>> upload(HttpServletRequest request) {
        try {
            FileParameter fileParameter = FileUtil.convertFileParameter((MultipartHttpServletRequest) request);
            return new Result<List<FileInfo>>().setData(this.uploadLocal(fileParameter));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }

    private List<FileInfo> uploadLocal(FileParameter fileParameter) throws ApplicationException {
        Map<String, List<FileInfo>> fileMap = localParse(fileParameter);
        List<FileInfo> newFiles = fileMap.get(MAP_KEY_ADD);
        List<FileInfo> result = fileInfoService.update(newFiles);
        for (FileInfo file : result) {
            file.setBytes(null);
            file.setPhysicalPath(null);
        }
        return result;
    }

    private Map<String, List<FileInfo>> localParse(FileParameter fileParameter) throws ApplicationException {
        return localParse(fileParameter.getBizCode(), fileParameter.getBizId(), fileParameter.getFiles());
    }

    private Map<String, List<FileInfo>> localParse(String bizCode, String bizId, FileInfo... files) throws ApplicationException {
        Map<String, List<FileInfo>> fileMap = new HashMap<>();
        List<FileInfo> addFiles = new ArrayList<>();
        fileMap.put(MAP_KEY_ADD, addFiles);
        if (StringUtils.isNotEmpty(bizCode) && StringUtils.isNotEmpty(bizId)
                && (files != null && files.length > 0)) {
            for (int i = 0, len = files.length; i < len; i++) {
                FileInfo file = files[i];
                if (file != null) {
                    file.setBizCode(bizCode);
                    file.setBizId(bizId);
                    if (StringUtils.isEmpty(file.getId())) {//新增
                        if (file.getDisplayIndex() == null) {
                            file.setDisplayIndex(10 * (i + 1));
                        }
                        file.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                        addFiles.add(file);
                    }
                    if (file.getBytes() != null) {
                        String file_root_path = uploadPath;
                        String file_name = "/" + UUID.randomUUID().toString().replaceAll("-", "");
                        BufferedOutputStream stream = null;
                        try {
                            java.io.File dir = new java.io.File(file_root_path + file_name);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            file_name = file_name + "/" + file.getId();
                            file_name += "." + file.getExtendName();
                            stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(file_root_path + file_name)));
                            stream.write(file.getBytes());
                        } catch (Exception e) {
                            logger.error("上传文件失败！[" + file.getBizCode() + ", " + file.getBizId() + ", " + file.getName() + "]", e);
                            throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
                        } finally {
                            close(stream);
                        }
                        file.setPhysicalPath(file_root_path + file_name);
                        file.setVisitPath(file_name);
                    }
                }
            }
        }
        return fileMap;
    }

    private void close(OutputStream stream) {
        if (null != stream) {
            try {
                stream.close();
            } catch (IOException e) {
                logger.error("文件流关闭错误", e);
            }
        }
    }
}
