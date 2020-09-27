package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.backend.User;


@Controller
class UserController {

    @Autowired
    private UserDao dao;

    @PostMapping("/users/new")
    public ResponseEntity<User> newUser(@RequestParam(value = "User") String input) throws IOException
    {
    	final User user = new ObjectMapper().readValue(input, User.class);
    	System.out.println("saving user: " + user);
    	dao.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/users/newUser")
    public ResponseEntity<User> NewUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
    		@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "address") String address) 
    				throws IOException
    {
    	User user = new User();    	
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setFirstName(firstname);
    	user.setLastName(lastname);
    	user.setAddress(address);
    	
    	
    	System.out.println("saving user: " + user);
    	dao.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {

    	System.out.println("-- loading all --");
        List<User> users = dao.loadAll();
        users.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
