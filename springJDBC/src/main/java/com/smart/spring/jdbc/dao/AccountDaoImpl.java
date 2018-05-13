package com.smart.spring.jdbc.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
  @Override
  public void outMoney(String from, Double money) {
    getJdbcTemplate().update("update t_account set money = money - ? where name = ?", money,from);
  }

  @Override
  public void inMoney(String to, Double money) {
    this.getJdbcTemplate().update("update t_account set money = money + ? where name = ?", money,to);
  }
}
