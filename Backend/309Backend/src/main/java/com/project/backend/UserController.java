package com.project.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    	return ResponseEntity.status(HttpStatus.OK)
    	        .body(user);
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {

    	System.out.println("-- loading all --");
        List<User> users = dao.loadAll();
        users.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK)
    	        .body(users);
    }
}
