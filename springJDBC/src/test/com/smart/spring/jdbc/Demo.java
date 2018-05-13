package com.smart.spring.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class Demo {
  //测试jdbc模板
  @Test
  public  void fun1() throws PropertyVetoException {

    //0 准备连接池
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    //dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sampledb");
    dataSource.setJdbcUrl("jdbc:mysql:///sampledb");
    dataSource.setUser("root");
    dataSource.setPassword("123456");

    //创建JDBC模板对象
    JdbcTemplate jt = new JdbcTemplate();
    jt.setDataSource(dataSource);

    //书写sql,并执行
    String sql ="insert into t_user values(1,'test','1233','1234','2018-05-12','192.168.0.1')";

    jt.update(sql);
  }
}



