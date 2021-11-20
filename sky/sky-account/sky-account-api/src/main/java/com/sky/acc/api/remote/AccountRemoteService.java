package com.sky.acc.api.remote;

import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.model.Account;
import com.sky.framework.api.dto.ResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AccountRemoteService {

    @PostMapping(value = "/account/create")
    ResponseDto<Boolean> create(@RequestBody Account account);

    @PostMapping(value = "/account/in")
    ResponseDto<Boolean> in(@RequestBody @Validated AccountDto dto);

    @PostMapping(value = "/account/out")
    ResponseDto<Boolean> out(@RequestBody @Validated AccountDto dto);
}
