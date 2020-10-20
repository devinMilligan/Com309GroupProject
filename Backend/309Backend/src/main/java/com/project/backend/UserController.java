package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.backend.User;


@Controller
@RequestMapping("/users")
class UserController {
	
	@Autowired
	private UserRepository userRepository;
    
    @PostMapping("/new")
    //Accept a User object via JSON Body, and save it to the database
    //Returns the user that was stored if successful
    public @ResponseBody User newUser(@RequestBody User UserDetails) {

    	//Before creating a new user, check if the email is already registered to an existing user
    	if (checkEmail(UserDetails.getEmail()))
    	{
    		//If email is already registered to an existing account, return a null user
        	System.out.println("Email already exists in database");
        	return null;
    	}
    	else
    	{
    		//if email is not registered, continue adding new user to the database
        	System.out.println("saving user: " + UserDetails);
        	userRepository.save(UserDetails);
        	return UserDetails;
    	}
    }
    
    @PostMapping("/update")
    //Update a User's details and save it to the database
    //A User's ID value cannot be updated/changed
    //The updated User object is returned if successful
    public @ResponseBody User updateUserInfo(@RequestBody User UserDetails) {

    	System.out.println("updating user: " + UserDetails.getId());
    	userRepository.save(UserDetails);
    	return UserDetails;
    }
    
    @GetMapping("/all")
    //Returns all User entries in the database in List form
    public @ResponseBody List<User> getAllUsers() {

    	System.out.println("-- loading all --");

    	//Convert Iterable result to returnable List
    	List<User> users = new ArrayList<User>();
    	for (User user : userRepository.findAll()) {
            users.add(user);
        }

        return users;
    }
    
    @GetMapping("/search")
    //Searches for users with the given email and password combination
    //Uses the automatically generated findByEmailAndPassword function from UserRepository class
    //Returns a list of all stores owned by the user with the provided ID
    public @ResponseBody List<User> searchUsers(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
    	
    	System.out.println("-- searching users --");

    	//Convert Iterable result to returnable List
        List<User> result = new ArrayList<User>();
   	 	for (User user : userRepository.findByEmailAndPassword(email, password)) {
   	 		result.add(user);
   	 	}
   	 	
        return result;
    }
    
    @GetMapping("/checkEmail")
    //Searches for users registered with the given email
    //Uses the automatically generated findByEmail function from UserRepository class
    //If there are more than 0 users registered with the email, returns TRUE, informing the client that the given email is already in use
    //If there are no users registered with the email, returns FALSE, informing the client that the given email able to be registered
    public @ResponseBody Boolean checkEmail(@RequestParam(value = "email") String email) {
    	
    	System.out.println("checking email: " + email);

    	//Check if there are any users with the given email
    	if (userRepository.findByEmail(email).size() > 0)
    		return true;
    	else
    		return false;
    }
}
