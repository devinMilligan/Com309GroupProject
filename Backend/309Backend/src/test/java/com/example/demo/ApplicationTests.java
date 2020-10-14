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

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {

	
	@Mock
	UserDao dao;

	@Test
	public void loadAll() {
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
