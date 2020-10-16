package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email, String password);
	List<User> findByEmail(String email);

}