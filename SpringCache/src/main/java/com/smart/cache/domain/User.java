package com.smart.cache.domain;

import java.io.Serializable;

public class User implements Serializable {
  private String userid;
  private String userName;
  private int age;

  public User(String userid){
    this.userid = userid;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
