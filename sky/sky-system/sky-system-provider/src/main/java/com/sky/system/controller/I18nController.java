package com.sky.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.framework.api.exception.CommonException;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;
import com.sky.system.api.remote.I18nRemoteService;
import com.sky.system.service.I18nService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "国际化信息")
@ApiSort(value = 500)
@RestController
public class I18nController implements I18nRemoteService {

    @Autowired
    private I18nService i18nService;

    @ApiOperation(value = "根据ID查询", position = 10)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<I18n> get(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.getById(dto.getId())).ok();
    }

    @ApiOperation(value = "保存", position = 20)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> save(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.create(dto));
    }

    @ApiOperation(value = "批量保存", position = 21)
    @ApiImplicitParam(name = "dto", value = "list", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> saveBatch(List<I18nDto> list) {
        return new ResponseDto<>(this.i18nService.create(list));
    }

    @ApiOperation(value = "修改", position = 30)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> update(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.update(dto));
    }

    @ApiOperation(value = "批量修改", position = 31)
    @ApiImplicitParam(name = "dto", value = "list", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> updateBatch(List<I18nDto> list) {
        return new ResponseDto<>(this.i18nService.update(list));
    }

    @ApiOperation(value = "删除", position = 40)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> delete(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.removeById(dto.getId()));
    }

    @ApiOperation(value = "批量删除", position = 41)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<Boolean> deleteBatch(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.removeByIds(dto.getIds()));
    }

    @ApiOperation(value = "分页列表", position = 50)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nQueryDto")
    @Override
    public ResponseDto<IPage<I18n>> page(I18nQueryDto dto) {
        return new ResponseDto<>(this.i18nService.page(dto));
    }

    @ApiOperation(value = "列表", position = 60)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @Override
    public ResponseDto<List<I18n>> list(I18nDto dto) {
        return new ResponseDto<>(this.i18nService.list(dto));
    }

    @ApiOperation(value = "测试国际化资源", position = 60)
    @ApiImplicitParam(name = "dto", value = "dto", required = true, dataType = "I18nDto")
    @PostMapping(value = "/i18n/testI18n")
    public ResponseDto<String> testI18n(@RequestBody I18nDto dto) {
        if (null == dto.getId()) {
            throw new CommonException(500, "system.i18n.testI18n_idIsNull", "system.i18n.testI18n_idIsNull");
        } else if (1 == dto.getId()) {
            throw new CommonException(500, "system.i18n.testI18n_idEqualsOne", "system.i18n.testI18n_idEqualsOne");
        }
        return new ResponseDto<String>().ok();
    }
}
