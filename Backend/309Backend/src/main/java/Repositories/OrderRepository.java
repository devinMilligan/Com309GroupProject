package Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ObjectClasses.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	Order findById(int id);
	List<Order> findByOrderingUser(int orderingUser);
	List<Order> findByOrderingUserAndStatus(int orderingUser, String status);
	List<Order> findByDeliveringUser(int deliveringUser);
	List<Order> findByStoreAndStatus(int store, String status);
	List<Order> findByStatus(String status);

}