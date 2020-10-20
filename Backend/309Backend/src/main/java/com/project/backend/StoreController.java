package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import com.project.backend.Store;


@Controller
@RequestMapping("/stores")
class StoreController {
	
	@Autowired
	private StoreRepository storeRepository;
    
	
    @PostMapping("/new")
    //Accept a Store object via JSON Body, and save it to the database
    //Returns the object that was stored if successful
    public @ResponseBody Store newStore(@RequestBody Store StoreDetails) {

    	System.out.println("saving store: " + StoreDetails);
    	storeRepository.save(StoreDetails);
    	return StoreDetails;
    }
    
    @PostMapping("/update")
    //Update a Store's details and save it to the database
    //A Store's ID value cannot be updated/changed
    //The updated Store object is returned if successful
    public @ResponseBody Store updateStore(@RequestBody Store StoreDetails) {

    	System.out.println("updating store: " + StoreDetails);
    	storeRepository.save(StoreDetails);
    	return StoreDetails;
    }
    
    @GetMapping("/getByManager")
    //Searches for all stores owned/managed by a particular user
    //Uses the automatically generated findByManager function from StoreRepository class
    //Returns a list of all stores owned by the user with the provided ID
    public @ResponseBody List<Store> getStoreByManager(@RequestParam(value = "managerID") int manager) {

    	System.out.println("searching stores by manager: " + manager);
    	
    	//Convert Iterable result to returnable List
        List<Store> result = new ArrayList<Store>();    	
   	 	for (Store store : storeRepository.findByManager(manager)) {
   	 		result.add(store);
   	 	}
   	 	
        return result;
    }
    
    @GetMapping("/all")
    //Returns all Store entries in the database in List form
    public @ResponseBody List<Store> getAllStores() {

    	System.out.println("-- loading all --");
    	
    	//Convert Iterable result to returnable List
    	List<Store> stores = new ArrayList<Store>();
    	for (Store store : storeRepository.findAll()) {
    		stores.add(store);
        }

        return stores;
    }
}
