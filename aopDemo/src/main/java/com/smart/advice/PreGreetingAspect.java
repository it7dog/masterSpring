package com.smart.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PreGreetingAspect {
  @Before("execution(* greeTo(..))")
  public void beforeGreeting(){
    System.out.println("How are you");
  }
}
