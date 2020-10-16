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
    public @ResponseBody Store createStore(@RequestBody Store StoreDetails) {
        Store input = new Store();
        input.setName(StoreDetails.getName());
        input.setAddress(StoreDetails.getAddress());
        input.setManager(StoreDetails.getManager());
        input.setLatitude(StoreDetails.getLatitude());
        input.setLongitude(StoreDetails.getLongitude());
        input.setHours(StoreDetails.getHours());

    	System.out.println("saving store: " + input);
    	storeRepository.save(input);
    	return input;
    }
    
    @PostMapping("/update")
    public @ResponseBody Store updateStore(@RequestBody Store StoreDetails) {
        Store input = new Store();
        input.setId(StoreDetails.getId());
        input.setName(StoreDetails.getName());
        input.setAddress(StoreDetails.getAddress());
        input.setManager(StoreDetails.getManager());
        input.setLatitude(StoreDetails.getLatitude());
        input.setLongitude(StoreDetails.getLongitude());
        input.setHours(StoreDetails.getHours());

    	System.out.println("updating store: " + input);
    	storeRepository.save(input);
    	return input;
    }
    
    @GetMapping("/getByManager")
    public @ResponseBody List<Store> getByManager(@RequestParam(value = "managerID") int manager) {

        List<Store> result = new ArrayList<Store>();
    	System.out.println("searching stores by manager: " + manager);
   	 	for (Store store : storeRepository.findByManager(manager)) {
   	 		result.add(store);
   	 	}
        result.forEach(System.out::println);
        return result;
    }
    
    @GetMapping("/all")
    public @ResponseBody List<Store> getAllStores() {

    	System.out.println("-- loading all --");
    	List<Store> stores = new ArrayList<Store>();
    	for (Store store : storeRepository.findAll()) {
    		stores.add(store);
        }
    	stores.forEach(System.out::println);
        return stores;
    }
}
