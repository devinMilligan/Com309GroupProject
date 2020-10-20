package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Integer> {

	//Create an auto-generated method to query Stores table by manager ID
	List<Store> findByManager(Integer manager);
	
}