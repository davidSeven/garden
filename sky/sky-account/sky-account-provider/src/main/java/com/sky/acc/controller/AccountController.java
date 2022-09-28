package com.sky.acc.controller;

import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.model.Account;
import com.sky.acc.api.remote.AccountRemoteService;
import com.sky.acc.service.AccountLockService;
import com.sky.framework.api.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账户")
@ApiSort(100)
@RestController
public class AccountController implements AccountRemoteService {

    @Autowired
    private AccountLockService accountLockService;

    @Override
    public ResponseDto<Boolean> create(Account account) {
        return new ResponseDto<>(this.accountLockService.create(account)).ok();
    }

    @Override
    public ResponseDto<Boolean> in(AccountDto dto) {
        return new ResponseDto<>(this.accountLockService.in(dto)).ok();
    }

    @Override
    public ResponseDto<Boolean> out(AccountDto dto) {
        return null;
    }
}
