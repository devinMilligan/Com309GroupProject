package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	List<MenuItem> findByid(int id);
	List<MenuItem> findByorderingUser(int orderingUser);
	List<MenuItem> findBydeliveringUser(int deliveringUser);
	List<MenuItem> findBystore(int store);
	List<MenuItem> findBystatus(String status);

}