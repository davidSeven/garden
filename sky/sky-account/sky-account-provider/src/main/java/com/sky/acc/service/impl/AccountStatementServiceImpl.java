package com.sky.acc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.acc.api.model.AccountStatement;
import com.sky.acc.dao.AccountStatementDao;
import com.sky.acc.service.AccountStatementService;
import org.springframework.stereotype.Service;

@Service
public class AccountStatementServiceImpl extends ServiceImpl<AccountStatementDao, AccountStatement> implements AccountStatementService {
}
