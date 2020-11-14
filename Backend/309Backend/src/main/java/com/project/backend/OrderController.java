package com.project.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.backend.Store.NotEnoughOrdersExeption;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private StoreRepository storeRepository;

    
    public class UserAlreadyHasActiveOrderException extends Exception {
		private static final long serialVersionUID = 1L;
    }
	
	@PostMapping("/new")
    public @ResponseBody Order newOrder(@RequestParam(value = "userID") int userID, @RequestParam(value = "storeID") int storeID) throws UserAlreadyHasActiveOrderException {

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
        
        Order thisOrder = orderRepository.findById(order);
        MenuItem thisItem = menuItemRepository.findById(foodID);
        
        double currTotal = thisOrder.getTotal();
        double price = thisItem.getPrice();
        
        thisOrder.setTotal(currTotal + (price * quantity));
        orderRepository.save(thisOrder);
        
        return item;
    }
	    
    @PostMapping("/cancel")
    public @ResponseBody String cancelOrder(@RequestParam(value = "orderID") int orderID) {

    	System.out.println("removing order: " + orderID);
    	
    	if (orderRepository.findById(orderID) != null) {
    		
    		orderRepository.deleteById(orderID);
    		Iterable<OrderItem> foodList = orderItemRepository.findByOrderID(orderID);
    		orderItemRepository.deleteAll(foodList);
    		
            return "true";    		
    	}
    	else
    		return "false";    	
    }
	
	@PostMapping("/advance")
    public @ResponseBody String advanceStatus(@RequestParam(value = "orderID") int order) throws NotEnoughOrdersExeption {

		Order thisOrder = orderRepository.findById(order);
		
		if (thisOrder.getStatus().equals("Active"))
		{
			thisOrder.setStatus("Submitted");
	        
			int storeID = thisOrder.getStore();
	        Store thisStore = storeRepository.findById(storeID);
	        
	        thisStore.addOrders(1);
	        
	        storeRepository.save(thisStore);
		}
		else if (thisOrder.getStatus().equals("Submitted"))
		{
			thisOrder.setStatus("InTransit");
			
			int storeID = thisOrder.getStore();
	        Store thisStore = storeRepository.findById(storeID);
	        
	        thisStore.removeOrders(1);
	        
	        storeRepository.save(thisStore);
		}
		else if (thisOrder.getStatus().equals("InTransit"))
			thisOrder.setStatus("Delivered");
		
        orderRepository.save(thisOrder);
        
        return thisOrder.getStatus();
    } 
	
	@PostMapping("/update")
    public @ResponseBody String updateStatus(@RequestParam(value = "orderID") int order, @RequestParam(value = "status") String status) throws NotEnoughOrdersExeption {

		Order thisOrder = orderRepository.findById(order);

		if (!(thisOrder.getStatus().equals(status)))
		{
			thisOrder.setStatus(status);
			
	        orderRepository.save(thisOrder);
	        
	        if (thisOrder.getStatus().equals("Submitted"))
			{	        
				int storeID = thisOrder.getStore();
		        Store thisStore = storeRepository.findById(storeID);
		        
		        thisStore.addOrders(1);
		        
		        storeRepository.save(thisStore);
			}
	        else if (thisOrder.getStatus().equals("InTransit"))
			{	        
				int storeID = thisOrder.getStore();
		        Store thisStore = storeRepository.findById(storeID);
		        
		        thisStore.removeOrders(1);
		        
		        storeRepository.save(thisStore);
			}
		}
        
        return thisOrder.getStatus();
    }
	
	@PostMapping("/deliver")
    public @ResponseBody void addDeliverer(@RequestParam(value = "orderID") int order, @RequestParam(value = "deliverID") int deliver) {

		Order thisOrder = orderRepository.findById(order);
		thisOrder.setDeliveringUser(deliver);
		orderRepository.save(thisOrder);
    }
    
    @GetMapping("/byStore")
    public @ResponseBody List<Order> getOrders(@RequestParam(value = "store") int storeID) {
    	
    	List<Order> orders = new ArrayList<Order>();
    	for (Order order : orderRepository.findByStoreAndStatus(storeID, "Submitted")) {
    		orders.add(order);
        }
    	
		return orders;
    }
    
    @GetMapping("/getStatus")
    public @ResponseBody String getStatus(@RequestParam(value = "id") int orderID) {    	    	
		return orderRepository.findById(orderID).getStatus();
    }
    
    @GetMapping("/byUser")
    public @ResponseBody List<Order> getUserOrders(@RequestParam(value = "user") int userID) {
    	
    	List<Order> orders = new ArrayList<Order>();
    	for (Order order : orderRepository.findByOrderingUser(userID)) {
    		orders.add(order);
        }
    	
		return orders;
    }
    
    @GetMapping("/byDeliverer")
    public @ResponseBody List<Order> getDelivererOrders(@RequestParam(value = "deliverer") int userID) {
    	
    	List<Order> orders = new ArrayList<Order>();
    	for (Order order : orderRepository.findByDeliveringUser(userID)) {
    		orders.add(order);
        }
    	
		return orders;
    }
}
