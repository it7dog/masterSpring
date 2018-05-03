package com.itcast.c_proxy;

import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;
import org.junit.Test;

public class Demo {
    @Test
    public void fun1() {
        UserService us = new UserServiceImpl();
        UserServiceProxyFactory factory = new UserServiceProxyFactory(us);

        UserService usProxy = factory.getUserService();

        usProxy.save();
    }
    @Test
    public void fun2(){

        UserServiceProxyCglibFactory factory = new UserServiceProxyCglibFactory();

        UserService usProxy = factory.getUserServiceProxy();

        usProxy.save();

        //判断代理对象是否属于被代理对象类型
        //代理对象继承了被代理对象
        System.out.println(usProxy instanceof UserServiceImpl);
    }
}
