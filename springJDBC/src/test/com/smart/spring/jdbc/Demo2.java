package com.smart.spring.jdbc;

import com.smart.spring.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:transaction/applicationContext2.xml")
public class Demo2 {

  @Resource(name = "accountService")
  private AccountService service;

  @Test
  public void fun1(){
    service.transfer("roy","james",100.00);
  }

}
