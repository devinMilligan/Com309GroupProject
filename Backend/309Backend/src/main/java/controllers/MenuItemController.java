package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ObjectClasses.MenuItem;
import Repositories.MenuItemRepository;

@RestController
@RequestMapping("/menu")
public class MenuItemController {
	
	@Autowired
	private MenuItemRepository menuRepository;
    
    @PostMapping("/new")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public MenuItem newItem(@RequestBody MenuItem ItemDetails) {

        System.out.println("adding item: " + ItemDetails);
        menuRepository.save(ItemDetails);
        return ItemDetails;
    }
    
    @GetMapping("/byStore")
    public @ResponseBody List<MenuItem> getStoreMenu(@RequestParam(value = "store") int storeID) {
    	
    	System.out.println("-- searching menus --");
    	
    	List<MenuItem> menu = new ArrayList<MenuItem>();
    	for (MenuItem food : menuRepository.findByStore(storeID)) {
            menu.add(food);
        }
    	
		return menu;
    }
    
    @GetMapping("/byID")
    public @ResponseBody MenuItem getByID(@RequestParam(value = "id") int foodID) {
    	
    	System.out.println("-- searching users --");
    	
    	if (menuRepository.findById(foodID) != null)
    		return menuRepository.findById(foodID);
    	else
    		return null;
    }
}
