package com.stream.garden.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.stream.garden.file.exception.FileExceptionCode;
import com.stream.garden.file.model.FileInfo;
import com.stream.garden.file.model.FileManage;
import com.stream.garden.file.model.FileParameter;
import com.stream.garden.file.service.IFileInfoService;
import com.stream.garden.file.service.IFileManageService;
import com.stream.garden.file.util.FileUtil;
import com.stream.garden.file.vo.FileInfoVO;
import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.framework.util.CollectionUtil;
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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author garden
 * @date 2019-09-26 15:38
 */
@Controller
@RequestMapping("/file/fileInfo")
public class FileInfoController {
    private Logger logger = LoggerFactory.getLogger(FileInfoController.class);

    @Autowired
    private IFileInfoService fileInfoService;

    @Autowired
    private IFileManageService fileManageService;

    @RequestMapping(value = "/download/{bizCode}/{bizId}")
    public void download(@PathVariable("bizCode") String bizCode, @PathVariable("bizId") String bizId, HttpServletResponse response) {
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
            response.reset();

            if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(bizId)) {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }

            // ??????????????????
            FileInfo paramFileInfo = new FileInfo();
            paramFileInfo.setBizCode(bizCode);
            paramFileInfo.setBizId(bizId);
            List<FileInfo> fileInfoList = fileInfoService.list(paramFileInfo);
            FileInfo fileInfo = null;
            if (CollectionUtil.isNotEmpty(fileInfoList)) {
                fileInfo = fileInfoList.get(0);
            }
            if (null == fileInfo) {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }

            File downFile = new File(fileInfo.getPhysicalPath());
            if (downFile.exists()) {
                byte[] downFileData = FileUtils.readFileToByteArray(downFile);
                if (null != downFileData && downFileData.length > 2) {
                    String resultFileName = fileInfo.getOriginalName();
                    response.setContentType("application/x-download");
                    response.addHeader("Content-Disposition", "attachment;filename=\"" + resultFileName + "\"");
                    response.addHeader("Content-Length", "" + downFileData.length);
                    stream.write(downFileData);
                }
            } else {
                throw new ApplicationException(ExceptionCode.UNKOWN_EXCEPTION);
            }
        } catch (Exception e) {
            logger.error("???????????????", e);
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
                    logger.error("????????????????????????", e);
                }
            }
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(@RequestBody FileInfo fileInfo) {
        try {
            // bizCode?????????????????????bizId?????????????????????bizCode?????????
            if (StringUtils.isEmpty(fileInfo.getBizCode())) {
                throw new ApplicationException(FileExceptionCode.FILE_INFO_BIZ_CODE_NOT_NULL);
            }
            return new Result<Integer>().ok().setData(this.fileInfoService.deleteByBiz(fileInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e);
        }
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public Result<List<FileInfo>> upload(HttpServletRequest request) {
        try {
            FileParameter fileParameter = FileUtil.convertFileParameter((MultipartHttpServletRequest) request);
            // ??????code????????????
            String fileManageCode = request.getParameter("fileCode");
            if (StringUtils.isEmpty(fileManageCode)) {
                throw new ApplicationException(FileExceptionCode.FILE_MANAGE_CODE_NOT_NULL);
            }
            FileManage fileManage = this.getFileManage(fileManageCode);
            if (null == fileManage) {
                throw new ApplicationException(FileExceptionCode.FILE_MANAGE_UNREGISTERED, fileManageCode);
            }
            fileParameter.setFileManageCode(fileManageCode);
            fileParameter.setFileManageId(fileManage.getId());
            fileParameter.setStorageType(fileManage.getStorageType());
            return new Result<List<FileInfo>>().ok().setData(this.fileInfoService.uploadFiles(fileParameter));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    /**
     * ?????????????????????id
     *
     * @param fileManageCode ?????????
     * @return id
     * @throws ApplicationException e
     */
    private String getFileManageId(String fileManageCode) throws ApplicationException {
        try {
            FileManage fileManage = this.getFileManage(fileManageCode);
            if (null != fileManage) {
                return fileManage.getId();
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    /**
     * ????????????????????????
     *
     * @param fileManageCode ?????????
     * @return ??????????????????
     * @throws ApplicationException e
     */
    private FileManage getFileManage(String fileManageCode) throws ApplicationException {
        try {
            FileManage paramFileManage = new FileManage();
            paramFileManage.setCode(fileManageCode);
            List<FileManage> fileManageList = this.fileManageService.list(paramFileManage);
            if (CollectionUtil.isNotEmpty(fileManageList)) {
                return fileManageList.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @GetMapping(value = "/toList")
    public String toList() {
        return "file/fileList";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<FileInfo>> pageList(FileInfoVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<FileInfo>>().setData(fileInfoService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

}
