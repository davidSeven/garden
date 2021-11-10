package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "订单信息")
@ApiSort(200)
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "导入", position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "__file", name = "file", value = "上传文件", required = true, allowMultiple = true)
    })
    public ResponseDto<Integer> dataImport(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");

        return new ResponseDto<Integer>().ok();
    }
}
