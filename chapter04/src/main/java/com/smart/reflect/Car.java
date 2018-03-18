package com.smart.reflect;

public class Car {
    private String brand;
    private String color;
    private int maxSpeed;

    //带参数的初始化方法
    public Car(String brand,String color, int maxSpeed){
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }
    //不带参数的初始化方法
    public Car(){

    }
    //不带参数的方法
    public void introduce(){
        System.out.println("brand:"+brand+"；color:"+color+";maxSpeed:"+maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
