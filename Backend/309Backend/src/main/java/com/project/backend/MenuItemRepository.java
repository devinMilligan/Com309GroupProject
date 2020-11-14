package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
	
	MenuItem findById(int id);
	List<MenuItem> findByStore(int store);

}