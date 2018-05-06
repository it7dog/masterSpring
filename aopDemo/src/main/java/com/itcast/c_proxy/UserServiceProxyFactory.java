package com.itcast.c_proxy;

import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceProxyFactory implements InvocationHandler {

  private UserService us;

  public UserServiceProxyFactory(UserService us) {
    this.us = us;
  }

  public UserService getUserService() {
    //生成动态代理
    UserService userService = (UserService) Proxy.newProxyInstance(UserServiceProxyFactory.class.getClassLoader(),
        UserServiceImpl.class.getInterfaces(), this);

    return userService;
  }

  public Object invoke(Object arg0, Method method, Object[] args2) throws Exception {
    System.out.println("打开事务");
    Object invoke = method.invoke(us, args2);
    System.out.println("关闭事务");
    return invoke;
  }
}
