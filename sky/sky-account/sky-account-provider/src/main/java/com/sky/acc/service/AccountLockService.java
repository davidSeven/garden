package com.sky.acc.service;

import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.model.Account;

public interface AccountLockService {

    boolean create(Account account);

    boolean in(AccountDto dto);

    boolean out(AccountDto dto);

    boolean freeze(AccountDto dto);

    boolean unFreeze(AccountDto dto);
}
