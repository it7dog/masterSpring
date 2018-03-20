package com.smart.reflect;

public class PrivateCar {
    //private成员变量，使用传统的类实例调用方式，只能在本类中访问
    private String color;

    //protectd方法，使用传统的类实例调用方式，智能在子类和本包中访问
    protected void drive() {
        System.out.println("drive private car! the color is:" + color);
    }
}
