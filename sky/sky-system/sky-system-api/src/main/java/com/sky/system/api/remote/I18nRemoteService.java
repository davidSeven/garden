package com.sky.system.api.remote;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.dto.I18nDto;
import com.sky.system.api.dto.I18nQueryDto;
import com.sky.system.api.model.I18n;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface I18nRemoteService {

    @PostMapping(value = "/i18n/get")
    ResponseDto<I18n> get(@RequestBody I18nDto dto);

    @PostMapping(value = "/i18n")
    ResponseDto<Boolean> save(@RequestBody I18nDto dto);

    @PostMapping(value = "/i18n/batch")
    ResponseDto<Boolean> saveBatch(@RequestBody List<I18nDto> list);

    @PutMapping(value = "/i18n")
    ResponseDto<Boolean> update(@RequestBody I18nDto dto);

    @PutMapping(value = "/i18n/batch")
    ResponseDto<Boolean> updateBatch(@RequestBody List<I18nDto> list);

    @DeleteMapping(value = "/i18n")
    ResponseDto<Boolean> delete(@RequestBody I18nDto dto);

    @DeleteMapping(value = "/i18n/batch")
    ResponseDto<Boolean> deleteBatch(@RequestBody I18nDto dto);

    @PostMapping(value = "/i18n/page")
    ResponseDto<IPage<I18n>> page(@RequestBody I18nQueryDto dto);

    @PostMapping(value = "/i18n/list")
    ResponseDto<List<I18n>> list(@RequestBody I18nDto dto);

}
