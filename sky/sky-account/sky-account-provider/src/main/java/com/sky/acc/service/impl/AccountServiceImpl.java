package com.sky.acc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.acc.api.dto.AccountDto;
import com.sky.acc.api.enums.AccountStateEnum;
import com.sky.acc.api.enums.AccountStatementTypeEnum;
import com.sky.acc.api.model.Account;
import com.sky.acc.api.model.AccountBalance;
import com.sky.acc.api.model.AccountStatement;
import com.sky.acc.dao.AccountDao;
import com.sky.acc.service.AccountBalanceService;
import com.sky.acc.service.AccountFreezeService;
import com.sky.acc.service.AccountService;
import com.sky.acc.service.AccountStatementService;
import com.sky.framework.api.exception.CommonException;
import com.sky.framework.utils.BeanHelpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    @Autowired
    private AccountStatementService accountStatementService;
    @Autowired
    private AccountBalanceService accountBalanceService;
    @Autowired
    private AccountFreezeService accountFreezeService;

    @Transactional
    @Override
    public void create(String customerCode) {
        LambdaQueryWrapper<Account> accountLambdaQueryWrapper = Wrappers.lambdaQuery();
        accountLambdaQueryWrapper.eq(Account::getCustomerCode, customerCode);
        if (super.count(accountLambdaQueryWrapper) == 0) {
            Account insertAccount = new Account();
            insertAccount.setCustomerCode(customerCode);
            insertAccount.setState(AccountStateEnum.ACTIVATE.name());
            super.save(insertAccount);
        }
    }

    @Transactional
    @Override
    public void in(AccountDto dto) {
        LambdaQueryWrapper<AccountStatement> accountStatementLambdaQueryWrapper = Wrappers.lambdaQuery();
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getCustomerCode, dto.getCustomerCode());
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getCurrency, dto.getCurrency());
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceType, dto.getInvoiceType());
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceNo, dto.getInvoiceNo());
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceLineNo, dto.getInvoiceLineNo());
        accountStatementLambdaQueryWrapper.eq(AccountStatement::getType, AccountStatementTypeEnum.IN.name());
        int count = this.accountStatementService.count(accountStatementLambdaQueryWrapper);
        if (count == 0) {
            LambdaQueryWrapper<AccountBalance> accountBalanceLambdaQueryWrapper = Wrappers.lambdaQuery();
            accountBalanceLambdaQueryWrapper.eq(AccountBalance::getCustomerCode, dto.getCustomerCode());
            accountBalanceLambdaQueryWrapper.eq(AccountBalance::getCurrency, dto.getCurrency());
            AccountBalance accountBalance = this.accountBalanceService.getOne(accountBalanceLambdaQueryWrapper);
            if (null == accountBalance) {
                AccountBalance insertAccountBalance = new AccountBalance();
                insertAccountBalance.setCustomerCode(dto.getCustomerCode());
                insertAccountBalance.setCurrency(dto.getCurrency());
                insertAccountBalance.setAvailableBalance(dto.getAmount());
                insertAccountBalance.setFreezeBalance(BigDecimal.ZERO);
                insertAccountBalance.setBalance(dto.getAmount());
                insertAccountBalance.setCreditBalance(BigDecimal.ZERO);
                insertAccountBalance.setOverdrawBalance(BigDecimal.ZERO);
                this.accountBalanceService.save(insertAccountBalance);
            } else {
                AccountBalance updateAccountBalance = new AccountBalance();
                updateAccountBalance.setId(accountBalance.getId());
                updateAccountBalance.setAvailableBalance(accountBalance.getAvailableBalance().add(dto.getAmount()));
                updateAccountBalance.setBalance(accountBalance.getBalance().add(dto.getAmount()));
                this.accountBalanceService.updateById(updateAccountBalance);
            }
            AccountStatement insertAccountStatement = BeanHelpUtil.convertDto(dto, AccountStatement.class);
            insertAccountStatement.setType(AccountStatementTypeEnum.IN.name());
            this.accountStatementService.save(insertAccountStatement);
        }
    }

    @Transactional
    @Override
    public void out(AccountDto dto) {
        if (dto.getFreeze()) {
            // 冻结
        } else {
            // 扣款
            LambdaQueryWrapper<AccountStatement> accountStatementLambdaQueryWrapper = Wrappers.lambdaQuery();
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getCustomerCode, dto.getCustomerCode());
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getCurrency, dto.getCurrency());
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceType, dto.getInvoiceType());
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceNo, dto.getInvoiceNo());
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getInvoiceLineNo, dto.getInvoiceLineNo());
            accountStatementLambdaQueryWrapper.eq(AccountStatement::getType, AccountStatementTypeEnum.OUT.name());
            int count = this.accountStatementService.count(accountStatementLambdaQueryWrapper);
            if (count == 0) {
                LambdaQueryWrapper<AccountBalance> accountBalanceLambdaQueryWrapper = Wrappers.lambdaQuery();
                accountBalanceLambdaQueryWrapper.eq(AccountBalance::getCustomerCode, dto.getCustomerCode());
                accountBalanceLambdaQueryWrapper.eq(AccountBalance::getCurrency, dto.getCurrency());
                AccountBalance accountBalance = this.accountBalanceService.getOne(accountBalanceLambdaQueryWrapper);
                if (null == accountBalance) {
                    throw new CommonException(500, "出账失败，账户没有余额信息");
                }
                if (accountBalance.getAvailableBalance().compareTo(dto.getAmount()) < 0) {
                    throw new CommonException(500, "出账失败，账户余额不足");
                }
                AccountBalance updateAccountBalance = new AccountBalance();
                updateAccountBalance.setId(accountBalance.getId());
                updateAccountBalance.setAvailableBalance(accountBalance.getAvailableBalance().subtract(dto.getAmount()));
                updateAccountBalance.setBalance(accountBalance.getBalance().subtract(dto.getAmount()));
                this.accountBalanceService.updateById(updateAccountBalance);
                AccountStatement insertAccountStatement = BeanHelpUtil.convertDto(dto, AccountStatement.class);
                insertAccountStatement.setType(AccountStatementTypeEnum.OUT.name());
                this.accountStatementService.save(insertAccountStatement);
            }
        }
    }

    @Override
    public void freeze(AccountDto dto) {

    }

    @Override
    public void unFreeze(AccountDto dto) {

    }
}
