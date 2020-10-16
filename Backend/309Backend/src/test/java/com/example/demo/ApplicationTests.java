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
		
		User exampleUser = new User(123, "email", "password", "address", "first", "last", "delivery", "image_path");

		assertEquals(123, exampleUser.getId());
		assertEquals("email", exampleUser.getEmail());
		assertEquals("password", exampleUser.getPassword());
		assertEquals("address", exampleUser.getAddress());
		assertEquals("first", exampleUser.getFirstName());
		assertEquals("last", exampleUser.getLastName());
		assertEquals("delivery", exampleUser.getType());
		assertEquals("image_path", exampleUser.getImagePath());
	}
	
	@Test
	public void setUserInfo() {
		
		User exampleUser = new User();
		
		exampleUser.setId(101);
		exampleUser.setEmail("example@gmail.com");
		exampleUser.setPassword("abc123");
		exampleUser.setAddress("123 Main St.");
		exampleUser.setFirstName("Joe");
		exampleUser.setLastName("Smith");
		exampleUser.setType("delivery");
		exampleUser.setImagePath("C://Pictures//image.png");

		assertEquals(101, exampleUser.getId());
		assertEquals("example@gmail.com", exampleUser.getEmail());
		assertEquals("abc123", exampleUser.getPassword());
		assertEquals("123 Main St.", exampleUser.getAddress());
		assertEquals("Joe", exampleUser.getFirstName());
		assertEquals("Smith", exampleUser.getLastName());
		assertEquals("delivery", exampleUser.getType());
		assertEquals("C://Pictures//image.png", exampleUser.getImagePath());
	}
	
	@Test
	public void loadAllUsers() {
		List<User> list = new ArrayList<User>();
		User acctOne = new User();
		User acctTwo = new User();
		User acctThree = new User();

		list.add(acctOne);
		list.add(acctTwo);
		list.add(acctThree);
		
		when(dao.loadAll()).thenReturn(list);

		List<User> users = dao.loadAll();

		assertEquals(3, users.size());
		verify(dao, times(1)).loadAll();
	}
	
	

}
