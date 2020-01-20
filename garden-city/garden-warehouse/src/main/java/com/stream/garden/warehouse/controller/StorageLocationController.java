package com.stream.garden.warehouse.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.warehouse.model.StorageLocation;
import com.stream.garden.warehouse.service.IStorageLocationService;
import com.stream.garden.warehouse.vo.StorageLocationVO;
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
 * @date 2020-01-02 19:03
 */
@Controller
@RequestMapping(value = "/warehouse/storageLocation")
public class StorageLocationController {

    private Logger logger = LoggerFactory.getLogger(StorageLocationController.class);

    @Autowired
    private IStorageLocationService storageLocationService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "storageLocation/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "storageLocation/edit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<StorageLocation>> pageList(StorageLocationVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<StorageLocation>>().setData(storageLocationService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(StorageLocation storageLocation) {
        try {
            return new Result<Integer>().setData(storageLocationService.insert(storageLocation)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExceptionCode.ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/addLock")
    @ResponseBody
    public Result<Integer> addLock(StorageLocation storageLocation) {
        try {
            return new Result<Integer>().setData(storageLocationService.insertLock(storageLocation)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExceptionCode.ADD_EXCEPTION);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(StorageLocation storageLocation) {
        try {
            return new Result<Integer>().ok().setData(storageLocationService.update(storageLocation));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, ExceptionCode.EDIT_EXCEPTION);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(StorageLocation storageLocation) {
        try {
            return new Result<Integer>().ok().setData(storageLocationService.delete(storageLocation.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
