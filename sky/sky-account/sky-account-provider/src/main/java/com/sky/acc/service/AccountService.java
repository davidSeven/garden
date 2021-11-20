package com.sky.acc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.model.Account;

public interface AccountService extends IService<Account> {

    void create(String customerCode);

    void in(AccountDto dto);

    void out(AccountDto dto);

    void freeze(AccountDto dto);

    void unFreeze(AccountDto dto);
}
