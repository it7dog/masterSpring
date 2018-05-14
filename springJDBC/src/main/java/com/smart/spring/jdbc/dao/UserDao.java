package com.smart.spring.jdbc.dao;

import com.smart.spring.bean.User;

import java.util.List;

public interface UserDao {
  /** 增
   * @param u 用户
   * */
  void save(User u);
  /* 删 */
  void delete(int id);
  //改
  void update(User u);
  //查
  void getById(int id);
  //查
  int getTotalCount();
  //查
  List<User> getAll();
}
