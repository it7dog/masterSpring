package com.itcast.aspect.test;

import com.smart.advice.NaiveWaiter;
import com.smart.advice.PreGreetingAspect;
import com.smart.advice.Waiter;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

//编程方式实现织入切面
public class AspectJProxyTest {
  @Test
  public void proxy(){
    Waiter waiter = new NaiveWaiter();
    AspectJProxyFactory factory = new AspectJProxyFactory();
    factory.setTarget(waiter);
    factory.addAspect(PreGreetingAspect.class);

    Waiter proxy = factory.getProxy();

    proxy.greeTo("John");
    proxy.serveTo("Tom");
  }
}
