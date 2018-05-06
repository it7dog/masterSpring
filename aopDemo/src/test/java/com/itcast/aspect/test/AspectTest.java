package com.itcast.aspect.test;

import com.smart.advice.Waiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com.smart.aspectj/applicationContext.xml")
public class AspectTest {
  @Resource(name = "waiter")
  private Waiter waiter;

  @Test
  public void fun1(){
    waiter.greeTo("Jhon");
    waiter.serveTo("Tom");
  }
}
