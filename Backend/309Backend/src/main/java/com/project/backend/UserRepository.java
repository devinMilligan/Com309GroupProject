package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	//Auto-generated function to query Users table in database by email and password columns
	List<User> findByEmailAndPassword(String email, String password);
	
	//Auto-generated function to query Users table in database by email column
	List<User> findByEmail(String email);

}