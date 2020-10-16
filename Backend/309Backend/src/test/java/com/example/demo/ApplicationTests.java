package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import com.project.backend.User;
import com.project.backend.UserDao;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {	

    @Mock
    JdbcTemplate jdbcTemplate;
    
	@Mock
	UserDao dao;

	String sql;
	
	@Test
	public void getUserInfo() {
		
		
	}
	
	@Test
	public void setUserInfo() {
		
		
	}
	
	@Test
	public void loadAllUsers() {
		
		
	}
	
	

}
