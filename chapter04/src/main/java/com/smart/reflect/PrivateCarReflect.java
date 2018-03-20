package com.smart.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateCarReflect {
    public static void main(String[] args) throws Throwable{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.smart.reflect.PrivateCar");
        PrivateCar pcar = (PrivateCar) clazz.newInstance();
        Field colorFld = clazz.getDeclaredField("color");

        //取消java语言访问检查以访问private变量
        colorFld.setAccessible(true);
        colorFld.set(pcar,"红色");

        Method dirveMtd =clazz.getDeclaredMethod("drive",(Class[])null);
        //取消java语言访问检查以访问protected方法
        dirveMtd.setAccessible(true);
        dirveMtd.invoke(pcar,(Object[])null);
    }
}
