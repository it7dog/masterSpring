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
}
