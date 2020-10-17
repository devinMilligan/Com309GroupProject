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
	public void getUserInfo() {
		
		User exampleUser = new User();
		exampleUser.setId(123);
		exampleUser.setEmail("email");
		exampleUser.setPassword("password");
		exampleUser.setAddress("address");
		exampleUser.setFirstName("first");
		exampleUser.setLastName("last");
		exampleUser.setType("delivery");
		exampleUser.setImagePath("image_path");

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