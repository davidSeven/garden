package com.sky.file.controller;

import cn.hutool.core.lang.UUID;
import com.sky.file.api.model.FileInfo;
import com.sky.file.service.FileInfoService;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.api.enums.ExceptionCode;
import com.sky.framework.api.exception.CommonException;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "文件")
@ApiSort(100)
@RestController
public class FileInfoController {
    private final Logger logger = LoggerFactory.getLogger(FileInfoController.class);

    private static final String SPLIT_SYMBOL = ".";

    @Autowired
    private FileInfoService fileInfoService;

    @ApiOperation(httpMethod = "POST", value = "文件上传")
    @PostMapping(value = "/file/upload")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "__file", name = "file", value = "上传的文件", required = true, allowMultiple = true)
    })
    public ResponseDto<List<FileInfo>> uploadFiles(HttpServletRequest request) {
        logger.debug("------------uploadFiles begin-------------------");
        long startTime = 0;
        if (logger.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(request)) {
            throw new CommonException(500, "不是文件上传表单");
        }
        // 验证不可以上传的文件
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 响应对象
        ResponseDto<List<FileInfo>> responseDto;
        try {
            responseDto = new ResponseDto<>(ExceptionCode.SUCCESS);
            List<FileInfo> fileList = new ArrayList<>();
            MultiValueMap<String, MultipartFile> multiFileMap = multipartHttpServletRequest.getMultiFileMap();
            if (multiFileMap.isEmpty() || CollectionUtils.isEmpty(multiFileMap.values())) {
                return null;
            }
            List<MultipartFile> multipartFileList = multiFileMap.values().iterator().next();
            if (CollectionUtils.isEmpty(multipartFileList)) {
                return null;
            }
            for (MultipartFile multipartFile : multipartFileList) {
                FileInfo file = new FileInfo();
                if (multipartFile.isEmpty()) {
                    continue;
                }
                // 上传的文件名
                file.setOriginalName(multipartFile.getOriginalFilename());
                file.setContentType(multipartFile.getContentType());
                file.setSize(multipartFile.getSize());
                // 文件扩展名
                if (StringUtils.isNotEmpty(file.getOriginalName())) {
                    file.setExtendName(getFileExtName(file.getOriginalName()));
                    if (StringUtils.isEmpty(file.getName())) {
                        file.setName(getFileName(file.getOriginalName()));
                    }
                }
                String physicalPathName = UUID.randomUUID().toString().replaceAll("-", "") + SPLIT_SYMBOL + file.getExtendName();
                File writeFile = new File("/tmp/" + physicalPathName);
                FileUtils.writeByteArrayToFile(writeFile, multipartFile.getBytes());
                file.setPhysicalPath(writeFile.getPath());
                fileList.add(file);
            }
            this.fileInfoService.saveBatch(fileList);
            responseDto.setData(fileList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CommonException(500, "上传文件失败");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("上传文件耗时：{}", (System.currentTimeMillis() - startTime));
        }
        logger.debug("------------uploadFiles end-------------------");
        return responseDto;
    }

    /**
     * 获取文件扩展名，不包含"."点
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public static String getFileExtName(String fileName) {
        if (fileName.lastIndexOf(SPLIT_SYMBOL) != -1) {
            return fileName.substring(fileName.lastIndexOf(SPLIT_SYMBOL) + 1);
        }
        return "";
    }

    /**
     * 获取文件名，不包含后缀
     *
     * @param fileName 文件名
     * @return 文件名
     */
    public static String getFileName(String fileName) {
        if (fileName.lastIndexOf(SPLIT_SYMBOL) != -1) {
            return fileName.substring(0, fileName.lastIndexOf(SPLIT_SYMBOL));
        }
        return fileName;
    }
}
