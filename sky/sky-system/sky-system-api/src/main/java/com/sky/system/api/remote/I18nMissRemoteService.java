package com.sky.system.api.remote;

import com.sky.framework.api.dto.ResponseDto;
import com.sky.system.api.model.I18nMiss;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface I18nMissRemoteService {

    @PostMapping(value = "/i18n-miss")
    ResponseDto<Boolean> save(@RequestBody I18nMiss i18nMiss);

}
