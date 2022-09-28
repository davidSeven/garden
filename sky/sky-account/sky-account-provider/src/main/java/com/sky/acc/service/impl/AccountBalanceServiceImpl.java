package com.sky.acc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.acc.api.model.AccountBalance;
import com.sky.acc.dao.AccountBalanceDao;
import com.sky.acc.service.AccountBalanceService;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceServiceImpl extends ServiceImpl<AccountBalanceDao, AccountBalance> implements AccountBalanceService {
}
