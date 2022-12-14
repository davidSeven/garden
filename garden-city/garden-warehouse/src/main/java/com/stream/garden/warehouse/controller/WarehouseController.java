package com.stream.garden.warehouse.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.warehouse.model.Warehouse;
import com.stream.garden.warehouse.service.IWarehouseService;
import com.stream.garden.warehouse.vo.WarehouseVO;
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
 * @date 2019-12-29 16:02
 */
@Controller
@RequestMapping(value = "/warehouse/warehouse")
public class WarehouseController {

    private Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "warehouse/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "warehouse/edit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Warehouse>> pageList(WarehouseVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Warehouse>>().setData(warehouseService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Warehouse warehouse) {
        try {
            return new Result<Integer>().setData(warehouseService.insert(warehouse)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e);
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Warehouse warehouse) {
        try {
            return new Result<Integer>().ok().setData(warehouseService.update(warehouse));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e);
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Warehouse warehouse) {
        try {
            return new Result<Integer>().ok().setData(warehouseService.delete(warehouse.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
