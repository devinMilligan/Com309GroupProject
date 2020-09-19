package com.project.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import com.project.backend.User;


@Controller
class UserController {

    @Autowired
    private UserDao dao;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users/new")
    public ResponseEntity<User> newUser()
    {
    	User user = User.create("danaw46", "abc123", "Dana", "Whitley", "464 Yellow Drive");
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
