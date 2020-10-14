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
    
    public JdbcTemplate getJDBC() {
    	return jdbcTemplate;
    }
    
    public void update(User user) {
        String sql = "UPDATE Users SET Email=?, Password=?, First_Name=?, Last_Name=?, Address=?, Account=?, Image=? WHERE id=?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getType(), user.getImagePath(), user.getId());
    }

    public void save(User user) {
        String sql = "insert into Users (Email, Password, First_Name, Last_Name, Address, Account, Image) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getType(), user.getImagePath());
    }

    public List<User> loadAll() {
        return jdbcTemplate.query("select * from Users", (resultSet, i) -> {
            return toUser(resultSet);
        });
    }
    
    public List<User> search(String email, String password) {
    	String sql = "SELECT * FROM Users WHERE email=(?) AND password=(?)";
        return jdbcTemplate.query(sql,
                new Object[]{email, password},
                (resultSet, i) -> {return toUser(resultSet);}
        );
    }
    
    public Boolean checkEmail(String email) {
    	String sql = "SELECT * FROM Users WHERE email=(?)";
    	List<User> list = jdbcTemplate.query(sql,
                new Object[]{email},
                (resultSet, i) -> {return toUser(resultSet);}
        );
    	
    	if (list.size() > 0)
    		return true;
    	else
    		return false;
    }

    private User toUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setAddress(resultSet.getString("ADDRESS"));
        user.setType(resultSet.getString("ACCOUNT"));
        user.setImagePath(resultSet.getString("IMAGE"));
        return user;
    }
}