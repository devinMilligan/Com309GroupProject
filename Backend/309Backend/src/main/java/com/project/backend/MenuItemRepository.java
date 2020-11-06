package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
	
	MenuItem findByid(int id);
	List<MenuItem> findBystore(int store);

}