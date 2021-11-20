package com.sky.acc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.acc.api.model.AccountFreeze;
import com.sky.acc.dao.AccountFreezeDao;
import com.sky.acc.service.AccountFreezeService;
import org.springframework.stereotype.Service;

@Service
public class AccountFreezeServiceImpl extends ServiceImpl<AccountFreezeDao, AccountFreeze> implements AccountFreezeService {
}
