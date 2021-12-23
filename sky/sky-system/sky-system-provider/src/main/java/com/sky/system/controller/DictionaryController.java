package com.sky.system.controller;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.DictionaryDto;
import com.sky.system.api.remote.DictionaryRemoteService;
import com.sky.system.api.vo.DictionaryVO;
import com.sky.system.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @date 2020-11-03 003 11:00
 */
@Api(tags = "数据字典信息")
@RestController
public class DictionaryController implements DictionaryRemoteService {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "保存")
    @ApiImplicitParam(name = "dto", value = "数据字典信息", required = true, dataType = "DictionaryDto")
    @Override
    public ResponseDto<Boolean> save(@RequestBody @Validated DictionaryDto dto) {
        return new ResponseDto<Boolean>().ok().setData(this.dictionaryService.save(dto));
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "dto", value = "数据字典信息", required = true, dataType = "DictionaryDto")
    @Override
    public ResponseDto<Boolean> update(@RequestBody DictionaryDto dto) {
        return new ResponseDto<Boolean>().ok().setData(this.dictionaryService.update(dto));
    }

    @ApiOperation(value = "列表")
    @ApiImplicitParam(name = "dto", value = "数据字典信息", required = true, dataType = "DictionaryDto")
    @Override
    public ResponseDto<List<DictionaryVO>> list(@RequestBody DictionaryDto dto) {
        return new ResponseDto<>(this.dictionaryService.listDictionary(dto)).ok();
    }

    @ApiOperation(value = "树列表")
    @ApiImplicitParam(name = "dto", value = "数据字典信息", required = true, dataType = "DictionaryDto")
    @Override
    public ResponseDto<List<DictionaryVO>> tree(DictionaryDto dto) {
        return new ResponseDto<>(this.dictionaryService.tree(dto)).ok();
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "dto", value = "数据字典信息", required = true, dataType = "DictionaryDto")
    @Override
    public ResponseDto<Integer> delete(@RequestBody DictionaryDto dto) {
        return new ResponseDto<Integer>().ok().setData(this.dictionaryService.physicalDeleteById(dto.getId()));
    }
}
