package com.smart.advice;

public class NaiveWaiter implements Waiter {
  @Override
  public void greeTo(String name) {
    System.out.println("greet to "+name+"...");
  }

  @Override
  public void serveTo(String name) {
    System.out.println("serving to "+name+"...");
  }
}
