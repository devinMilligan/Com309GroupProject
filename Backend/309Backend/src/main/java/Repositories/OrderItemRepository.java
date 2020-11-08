package Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ObjectClasses.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
	
	List<OrderItem> findByOrderID(int orderID);

}