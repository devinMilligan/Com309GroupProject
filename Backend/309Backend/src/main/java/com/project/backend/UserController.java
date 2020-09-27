package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import com.project.backend.User;


@Controller
class UserController {

    @Autowired
    private UserDao dao;

    @PostMapping("/users/newUser")
    public ResponseEntity<User> NewUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
    		@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "address") String address, 
    		@RequestParam(value = "type") String type, @RequestParam(value = "image") String image) 
    				throws IOException
    {
    	User user = new User();    	
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setFirstName(firstname);
    	user.setLastName(lastname);
    	user.setAddress(address);
    	user.setType(type);
    	user.setImagePath(image);
    	
    	
    	System.out.println("saving user: " + user);
    	dao.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @PostMapping("/users/new")
    public ResponseEntity<User> createUser(@RequestBody User UserDetails) {
        User input = new User();
        input.setEmail(UserDetails.getEmail());
        input.setPassword(UserDetails.getPassword());
        input.setFirstName(UserDetails.getFirstName());
        input.setLastName(UserDetails.getLastName());
        input.setAddress(UserDetails.getAddress());
        input.setType(UserDetails.getType());
        input.setImagePath(UserDetails.getImagePath());

    	System.out.println("saving user: " + input);
    	dao.save(input);
    	return ResponseEntity.status(HttpStatus.OK).body(input);
    }
    
    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {

    	System.out.println("-- loading all --");
        List<User> users = dao.loadAll();
        users.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    
    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
    	
    	System.out.println("-- searching users --");
        List<User> result = dao.search(email, password);
        result.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
