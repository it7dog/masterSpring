package com.itcast.aspect.test;

import com.itcast.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com.itcast.annotation/applicationContext.xml")

public class Demo1 {
  @Resource(name = "userService")
  private UserService us;

  @Test
  public void fun1() {
    us.save();
  }
}
