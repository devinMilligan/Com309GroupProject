package com.project.backend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	Order findByid(int id);
	List<Order> findByorderingUser(int orderingUser);
	List<Order> findBydeliveringUser(int deliveringUser);
	List<Order> findBystore(int store);
	List<Order> findBystatus(String status);

}