package com.stream.garden.system.position.controller;

import com.stream.garden.framework.api.exception.ExceptionCode;
import com.stream.garden.framework.api.model.PageInfo;
import com.stream.garden.framework.api.model.Result;
import com.stream.garden.framework.api.vo.Criteria;
import com.stream.garden.system.exception.SystemExceptionCode;
import com.stream.garden.system.position.model.Position;
import com.stream.garden.system.position.service.IPositionService;
import com.stream.garden.system.position.vo.PositionVO;
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
 * @date 2019-07-20 15:47
 */
@Controller
@RequestMapping(value = "/system/position")
public class PositionController {
    private Logger logger = LoggerFactory.getLogger(PositionController.class);

    @Autowired
    private IPositionService positionService;


    /**
     * 跳转列表页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toList")
    public String toList() {
        return "system/position/list";
    }

    /**
     * 跳转编辑页面
     *
     * @return 页面路径
     */
    @GetMapping(value = "/toEdit")
    public String toEdit() {
        return "system/position/edit";
    }

    @PostMapping(value = "/pageList")
    @ResponseBody
    public Result<PageInfo<Position>> pageList(PositionVO vo) {
        try {
            if (null == vo.getCriteria()) {
                vo.setCriteria(new Criteria<>());
            }
            vo.asOrderByUpdationDate();
            return new Result<PageInfo<Position>>().setData(positionService.pageList(vo)).ok();
        } catch (Exception e) {
            logger.error(">>>" + e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION);
        }
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Result<Integer> add(Position position) {
        try {
            return new Result<Integer>().setData(positionService.insert(position)).ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.POSITION_ADD_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Result<Integer> edit(Position position) {
        try {
            return new Result<Integer>().ok().setData(positionService.update(position));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(e, SystemExceptionCode.POSITION_EDIT_EXCEPTION.getAppCode(e));
        }
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Result<Integer> delete(Position position) {
        try {
            return new Result<Integer>().ok().setData(positionService.delete(position.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<>(ExceptionCode.UNKOWN_EXCEPTION.getAppCode(e));
        }
    }
}
