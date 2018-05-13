package com.smart.spring.service;

import com.smart.spring.jdbc.dao.AccountDao;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = true)
public class AccountServiceImpl implements AccountService {
  // 业务层注入 DAO:
  private AccountDao accountDao;
  private TransactionTemplate tt;


  public void setAccountDao(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

//  @Override
//  public void transfer(final  String from, final  String to, final  Double money) {
//    tt.execute(new TransactionCallbackWithoutResult() {
//      @Override
//      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//        accountDao.outMoney(from, money);
//        int i = 1/0;
//        accountDao.inMoney(to, money);
//      }
//    });
//
//  }

  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false)
  public void transfer(final  String from, final  String to, final  Double money) {
    //减
    accountDao.outMoney(from, money);
    //加
    accountDao.inMoney(to, money);

  }

  public TransactionTemplate getTt() {
    return tt;
  }

  public void setTt(TransactionTemplate tt) {
    this.tt = tt;
  }
}
