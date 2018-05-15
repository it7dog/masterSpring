package com.smart.cache.mycache;

import com.smart.cache.mycache.UserService;

public class UserMain {
  public static void main(String[] args){
    UserService userService = new UserService();
    //第一次查询，应该是数据库查询
    userService.getUserById("001001");
    //第二次查询，应该直接从缓存返回
    userService.getUserById("001001");
    //重置缓存
    userService.reload();

    System.out.println("after reload ...");

    //第一次查询，应该是数据库查询
    userService.getUserById("001001");
    //第二次查询，应该直接从缓存返回
    userService.getUserById("001001");

  }
}
