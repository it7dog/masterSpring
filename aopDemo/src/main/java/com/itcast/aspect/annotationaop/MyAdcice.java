package com.itcast.aspect.annotationaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

//通知类
@Aspect
//表示该类是一个通知类
public class MyAdcice {
  @Pointcut("execution(* com.itcast.service.impl.*ServiceImpl.*(..))")
  public void pc(){}
  //前置通知
  //指定该方法是前置通知,并制定切入点
  @Before("MyAdcice.pc()")
  public void before(){
    System.out.println("这是前置通知!!");
  }
  //后置通知
  @AfterReturning("execution(* com.itcast.service.impl.*ServiceImpl.*(..))")
  public void afterReturning(){
    System.out.println("这是后置通知(如果出现异常不会调用)!!");
  }
  //环绕通知
  @Around("execution(* com.itcast.service.impl.*ServiceImpl.*(..))")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("这是环绕通知之前的部分!!");
    Object proceed = pjp.proceed();//调用目标方法
    System.out.println("这是环绕通知之后的部分!!");
    return proceed;
  }
  //异常通知
  @AfterThrowing("execution(* com.itcast.service.impl.*ServiceImpl.*(..))")
  public void afterException(){
    System.out.println("出事啦!出现异常了!!");
  }
  //后置通知
  @After("execution(* com.itcast.service.impl.*ServiceImpl.*(..))")
  public void after(){
    System.out.println("这是后置通知(出现异常也会调用)!!");
  }
}
