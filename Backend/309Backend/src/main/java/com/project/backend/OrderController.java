package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	@PostMapping("/new")
    public @ResponseBody Order newOrder(@RequestParam(value = "userID") int userID, @RequestParam(value = "storeID") int storeID) {

		Order order = new Order(userID, storeID, "Active");
		
        System.out.println("adding order: " + order.getId());
        orderRepository.save(order);
        return order;
    }
	
	@PostMapping("/addItem")
    public @ResponseBody OrderItem addItem(@RequestParam(value = "Order") int order, @RequestParam(value = "Item") int foodID, @RequestParam(value = "Quantity") int quantity) {

		OrderItem item = new OrderItem(order, foodID, quantity);
		
        System.out.println("adding item: " + item.getId());
        orderItemRepository.save(item);
        return item;
    }
}
