package com.smart.spring.jdbc.dao;

import com.smart.spring.bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//JdbcDaoSupport 根据链接池创建JDBC模板
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

  //private JdbcTemplate jt;

  @Override
  public void save(User u) {

    //书写sql,并执行
    String sql = "insert into t_user values(null,?,?,'1234','2018-05-12','192.168.0.1')";
    //jt.update(sql, u.getUserName(), u.getPassword());
    super.getJdbcTemplate().update(sql, u.getUserName(), u.getPassword());
  }

  @Override
  public void delete(int id) {
    //书写sql,并执行
    String sql = "DELETE  from t_user where user_id=?";
    //jt.update(sql, id);

    super.getJdbcTemplate().update(sql, id);
  }

  @Override
  public void update(User u) {
    //书写sql,并执行
    String sql = "UPDATE t_user set user_name=? where user_id=?";
    //jt.update(sql, u.getUserName(), u.getUserId());
    super.getJdbcTemplate().update(sql, u.getUserName(), u.getUserId());
  }

  @Override
  public void getById(int id) {
    //书写sql,并执行
    String sql = "select * from t_user where id=?";
   /* jt.queryForObject(sql, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setUserId(resultSet.getInt("user_id"));
        u.setUserName(resultSet.getString("user_name"));
        return u;
      }
    }, id);*/

    super.getJdbcTemplate().queryForObject(sql, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setUserId(resultSet.getInt("user_id"));
        u.setUserName(resultSet.getString("user_name"));
        return u;
      }
    }, id);
  }

  @Override
  public int getTotalCount() {
    //书写sql,并执行
    String sql = "select count(*) from t_user";
    //int count = jt.queryForObject(sql, Integer.class);

    int count = super.getJdbcTemplate().queryForObject(sql, Integer.class);

    return count;
  }

  @Override
  public List<User> getAll() {
    //书写sql,并执行
    String sql = "select * from t_user";
//    List<User> users = jt.query(sql, new RowMapper<User>() {
//      @Override
//      public User mapRow(ResultSet resultSet, int i) throws SQLException {
//        User u = new User();
//        u.setUserId(resultSet.getInt("user_id"));
//        u.setUserName(resultSet.getString("user_name"));
//        return u;
//      }
//    });
    List<User> users = super.getJdbcTemplate().query(sql, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setUserId(resultSet.getInt("user_id"));
        u.setUserName(resultSet.getString("user_name"));
        return u;
      }
    });
    return users;
  }

  /*public void setJt(JdbcTemplate jt) {
    this.jt = jt;
  }*/
}
