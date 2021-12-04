package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.GenerateNumberDto;
import com.sky.system.api.dto.SerialNumberQueryDto;
import com.sky.system.api.model.SerialNumber;
import com.sky.system.api.remote.SerialNumberRemoteService;
import com.sky.system.service.MongoSerialNumberService;
import com.sky.system.service.SerialNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "流水号信息")
@ApiSort(100)
@RestController
public class SerialNumberController implements SerialNumberRemoteService {

    @Autowired
    private SerialNumberService serialNumberService;
    @Autowired
    private MongoSerialNumberService mongoDbSerialNumberService;

    @ApiOperation(value = "流水号信息", position = 1)
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @Override
    public ResponseDto<SerialNumber> get(Long id) {
        SerialNumber serialNumber = this.serialNumberService.getById(id);
        return new ResponseDto<>(serialNumber).ok();
    }

    @ApiOperation(value = "保存", position = 2)
    @ApiImplicitParam(name = "serialNumber", value = "参数", required = true, dataType = "SerialNumber")
    @Override
    public ResponseDto<Boolean> save(@RequestBody SerialNumber serialNumber) {
        return new ResponseDto<>(this.serialNumberService.save(serialNumber)).ok();
    }

    @ApiOperation(value = "修改", position = 3)
    @ApiImplicitParam(name = "serialNumber", value = "参数", required = true, dataType = "SerialNumber")
    @Override
    public ResponseDto<Boolean> update(@RequestBody SerialNumber serialNumber) {
        return new ResponseDto<>(this.serialNumberService.updateById(serialNumber)).ok();
    }

    @ApiOperation(value = "分页", position = 4)
    @ApiImplicitParam(name = "dto", value = "参数", required = true, dataType = "SerialNumberQueryDto")
    @Override
    public ResponseDto<IPage<SerialNumber>> page(@RequestBody SerialNumberQueryDto dto) {
        return new ResponseDto<>(this.serialNumberService.page(dto)).ok();
    }

    // 60 * 3000
    // 流水号接口	180000	208	195	266	280	319	3	612	0.0	275.3047087811496	77.16059709002923	82.53764218341105
    @ApiOperation(value = "生成流水号", position = 5, notes = "并发结果：40个线程，每个线程执行300次，执行时间30S<br/>" +
            "样本：12000 平均值：50 中位数：45 90%百分位：89 95%百分位：93 99%百分位：100 最小值：4 最大值：180 异常%：0 吞吐量：268.6/sec")
    @ApiImplicitParam(name = "dto", value = "业务编号", required = true, dataType = "GenerateNumberDto")
    @Override
    public ResponseDto<String> generateNumber(@RequestBody GenerateNumberDto dto) {
        return new ResponseDto<>(this.serialNumberService.generateNumber(dto.getCode())).ok();
    }

    @ApiOperation(value = "生成流水号-批量", position = 6)
    @ApiImplicitParam(name = "dto", value = "业务编号", required = true, dataType = "GenerateNumberDto")
    @Override
    public ResponseDto<List<String>> generateNumbers(@RequestBody GenerateNumberDto dto) {
        return new ResponseDto<>(this.serialNumberService.generateNumbers(dto.getCode(), dto.getNum())).ok();
    }

    @ApiOperation(value = "生成流水号", position = 7, notes = "使用Mongo进行数据处理")
    @ApiImplicitParam(name = "dto", value = "业务编号", required = true, dataType = "GenerateNumberDto")
    @Override
    public ResponseDto<String> generateNumber2(@RequestBody GenerateNumberDto dto) {
        return new ResponseDto<>(this.mongoDbSerialNumberService.generateNumber(dto.getCode())).ok();
    }

    @ApiOperation(value = "数据同步", position = 8, notes = "将Mongo的数据同步到DB")
    @PostMapping(value = "/serial-number/asyncData")
    public ResponseDto<Integer> asyncData() {
        return new ResponseDto<>(this.serialNumberService.asyncData()).ok();
    }

    @ApiOperation(value = "数据清理", position = 9, notes = "清理Mongo里面的数据")
    @PostMapping(value = "/serial-number/clearData")
    public ResponseDto<Long> clearData() {
        return new ResponseDto<>(this.mongoDbSerialNumberService.remove()).ok();
    }
}
