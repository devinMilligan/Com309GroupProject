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

import com.project.backend.Store;
import com.project.backend.StoreRepository;
import com.project.backend.User;
import com.project.backend.UserRepository;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {
	
	@Mock
	UserRepository UserRepo;
	
	@Mock
	StoreRepository StoreRepo;
	
	@Test
	public void findUserByEmail() {
		
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(100);
    	user.setEmail("user@example.com");
    	user.setPassword("abc123");
    	users.add(user);
		
		when(UserRepo.findByEmail("user@example.com")).thenReturn(users);

		List<User> USERS = UserRepo.findByEmail("user@example.com");
		User USER = USERS.get(0);

		assertEquals(100, USER.getId());
		assertEquals("abc123", USER.getPassword());
		assertEquals("user@example.com", USER.getEmail());
	}

	@Test
	public void getAllUsersTest() {
		List<User> list = new ArrayList<User>();
		User acctOne = new User();
		User acctTwo = new User();
		User acctThree = new User();

		list.add(acctOne);
		list.add(acctTwo);
		list.add(acctThree);

		when(UserRepo.findAll()).thenReturn(list);

		List<User> acctList = new ArrayList<User>();
    	for (User user : UserRepo.findAll()) {
    		acctList.add(user);
        }

		assertEquals(3, acctList.size());
		verify(UserRepo, times(1)).findAll();
	}
	
	@Test
	public void findStoreByManager() {
		
		List<Store> stores = new ArrayList<Store>();
		Store store = new Store();
		store.setId(100);
		store.setName("MyStore");
		store.setManager(86);
		stores.add(store);
		
		when(StoreRepo.findByManager(86)).thenReturn(stores);

		List<Store> STORES = StoreRepo.findByManager(86);
		Store STORE = STORES.get(0);

		assertEquals(100, STORE.getId());
		assertEquals("MyStore", STORE.getName());
		assertEquals(86, STORE.getManager());
	}

	@Test
	public void getAllStoresTest() {
		List<Store> list = new ArrayList<Store>();
		Store storeOne = new Store();
		Store storeTwo = new Store();
		Store storeThree = new Store();

		list.add(storeOne);
		list.add(storeTwo);
		list.add(storeThree);

		when(StoreRepo.findAll()).thenReturn(list);

		List<Store> storeList = new ArrayList<Store>();
    	for (Store store : StoreRepo.findAll()) {
    		storeList.add(store);
        }

		assertEquals(3, storeList.size());
		verify(StoreRepo, times(1)).findAll();
	}


}