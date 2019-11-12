package com.stream.garden.file.controller;

import com.stream.garden.file.exception.FileExceptionCode;
import com.stream.garden.file.model.FileManage;
import com.stream.garden.file.service.IFileManageService;
import com.stream.garden.file.vo.FileManageVO;
import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author garden
 * @date 2019-09-26 14:25
 */
@Controller
@RequestMapping("/file/fileManage")
public class FileManageController {
    private Logger logger = LoggerFactory.getLogger(FileManageController.class);

    @Autowired
    private IFileManageService fileManageService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "file/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "file/edit";
    }

    @GetMapping(value = "/toChooseImg")
    public String toChooseImg() {
        return "file/chooseImg";
    }

    @GetMapping(value = "/toChooseFile")
    public String toChooseFile() {
        return "file/chooseFile";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<FileManage>> pageList(FileManageVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<FileManage>>().setData(fileManageService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(FileManage fileManage) {
        try {
            return new Result<Integer>().setData(fileManageService.insert(fileManage)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, FileExceptionCode.FILE_MANAGE_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(FileManage fileManage) {
        try {
            return new Result<Integer>().ok().setData(fileManageService.update(fileManage));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, FileExceptionCode.FILE_MANAGE_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(FileManage fileManage) {
        try {
            return new Result<Integer>().ok().setData(fileManageService.delete(fileManage.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
