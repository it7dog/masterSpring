package com.itcast.advice;

import com.smart.advice.GreetingBeforeAdvice;
import com.smart.advice.NaiveWaiter;
import com.smart.advice.Waiter;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdviceTest {
  @Test
  public void before(){
    Waiter target = new NaiveWaiter();
    BeforeAdvice advice = new GreetingBeforeAdvice();

    ProxyFactory pf = new ProxyFactory();

    pf.setTarget(target);

    pf.addAdvice(advice);

    Waiter proxy = (Waiter) pf.getProxy();

    proxy.greeTo("Jhon");
    proxy.serveTo("Tom");
  }
}
