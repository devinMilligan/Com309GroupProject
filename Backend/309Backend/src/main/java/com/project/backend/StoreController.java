package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Store> updateStore(@RequestBody Store StoreDetails) {
        Store input = new Store();
        input.setId(StoreDetails.getId());
        input.setName(StoreDetails.getName());
        input.setAddress(StoreDetails.getAddress());
        input.setManager(StoreDetails.getManager());
        input.setLatitude(StoreDetails.getLatitude());
        input.setLongitude(StoreDetails.getLongitude());
        input.setHours(StoreDetails.getHours());

    	System.out.println("updating store: " + input);
    	return ResponseEntity.status(HttpStatus.OK).body(input);
    }
    
    @GetMapping("/getByManager")
    public ResponseEntity<List<Store>> getByManager(@RequestParam(value = "managerID") int manager) {
        
    	System.out.println("-- searching --");
		return null;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStores() {

    	System.out.println("-- loading all --");
		return null;
    }
}
