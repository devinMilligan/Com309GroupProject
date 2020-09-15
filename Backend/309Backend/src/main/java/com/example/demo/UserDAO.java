package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void registerUser(User user) {
      String sql = "insert into Users (username, password) values (user.getUsername() " + "user.getPassword())";
      jdbcTemplate.update(sql);
  }
  
  public List<User> getUsers() {
      return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
          return toUser(resultSet);
      });
  }
  
  private User toUser(ResultSet resultSet) throws SQLException {
      User person = new User();
      person.setUsername(resultSet.getString("username"));
      person.setPassword(resultSet.getString("password"));
      return person;
  }
}