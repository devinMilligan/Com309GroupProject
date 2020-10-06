package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.project.backend.Store;


@Controller
class StoreController {

    @Autowired
    private StoreDao dao;
    
    @PostMapping("/stores/new")
    public ResponseEntity<Store> createStore(@RequestBody Store StoreDetails) {
        Store input = new Store();
        input.setName(StoreDetails.getName());
        input.setAddress(StoreDetails.getAddress());
        input.setManager(StoreDetails.getManager());
        input.setLatitude(StoreDetails.getLatitude());
        input.setLongitude(StoreDetails.getLongitude());
        input.setHours(StoreDetails.getHours());

    	System.out.println("saving store: " + input);
    	dao.save(input);
    	return ResponseEntity.status(HttpStatus.OK).body(input);
    }
    
    @GetMapping("/stores/getByManager")
    public ResponseEntity<List<Store>> getByManager(@RequestParam(value = "managerID") int manager) {
        
    	System.out.println("-- searching --");
        List<Store> stores = dao.search(manager);
        stores.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
    
    @GetMapping("/stores/all")
    public ResponseEntity<List<Store>> getAllStores() {

    	System.out.println("-- loading all --");
        List<Store> stores = dao.loadAll();
        stores.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }
}
