package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "insert into Users (first_Name, Last_Name, Address) values (?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(),
                user.getAddress());
    }

    public List<User> loadAll() {
        return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
            return toUser(resultSet);
        });
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setAddress(resultSet.getString("ADDRESS"));
        return user;
    }
}