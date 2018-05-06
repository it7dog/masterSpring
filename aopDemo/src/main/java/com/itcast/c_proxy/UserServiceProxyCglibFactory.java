package com.itcast.c_proxy;

import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceProxyCglibFactory implements MethodInterceptor {

    public UserService getUserServiceProxy(){
        Enhancer en = new Enhancer();  //帮我们生成代理对象

        en.setSuperclass(UserServiceImpl.class); //设置对谁进行代理

        en.setCallback(this); //代理要干什么

        UserService us = (UserService) en.create(); //创建代理对象

        return us;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //打开事务
        System.out.println("打开事务");
        //调用原有方法
        Object returnValue = methodProxy.invokeSuper(o,objects);
        //提交事务
        System.out.println("提交事务");

        return returnValue;
    }
}
