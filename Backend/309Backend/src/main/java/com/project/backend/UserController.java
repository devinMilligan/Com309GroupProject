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

    @PostMapping("/newUser")
    public @ResponseBody User createUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
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

    	if (checkEmail(user.getEmail()) == "true")
    	{
        	System.out.println("Email already exists in database");
        	return null;
    	}
    	else
    	{
        	System.out.println("saving user: " + user);
        	userRepository.save(user);
        	return user;
    	}
    }
	
    @PostMapping("/updateUser")
    public @ResponseBody User updateUser(@RequestParam(value = "id") int id, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
    		@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "address") String address, 
    		@RequestParam(value = "type") String type, @RequestParam(value = "image") String image) 
    				throws IOException
    {
    	User user = new User();
    	user.setId(id);
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setFirstName(firstname);
    	user.setLastName(lastname);
    	user.setAddress(address);
    	user.setType(type);
    	user.setImagePath(image);
    	
    	
    	System.out.println("updating user: " + user.getId());
    	userRepository.save(user);
    	return user;
    }
    
    @PostMapping("/new")
    //Accept a User object via JSON Body, and save it to the database
    //Returns the user that was stored if successful
    public @ResponseBody User newUser(@RequestBody User UserDetails) {

    	//Before creating a new user, check if the email is already registered to an existing user
    	if (checkEmail(UserDetails.getEmail()) == "true")
    	{
    		//If email is already registered to an existing account, return a null user
        	System.out.println("Email already exists in database");
        	return null;
    	}
    	else
    	{
        	System.out.println("Saving user: " + UserDetails.toString());
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
	
    @PostMapping("/changePassword")
    public @ResponseBody User changePassword(@RequestParam(value = "id") int id, @RequestParam(value = "newPassword") String password) throws IOException
    {
    	User user = userRepository.findByid(id).get(0);
    	user.setPassword(password);    	
    	
    	System.out.println("changing password: " + user.getPassword());
    	userRepository.save(user);
    	return user;
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
    public @ResponseBody User searchUsers(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
    	
    	System.out.println("-- searching users --");
    	
    	if (userRepository.findByEmailAndPassword(email, password).size() > 0)   	 	
    		return userRepository.findByEmailAndPassword(email, password).get(0);
    	else
    		return null;
    }
    
    @GetMapping("/checkEmail")
    //Searches for users registered with the given email
    //Uses the automatically generated findByEmail function from UserRepository class
    //If there are more than 0 users registered with the email, returns TRUE, informing the client that the given email is already in use
    //If there are no users registered with the email, returns FALSE, informing the client that the given email able to be registered
    public @ResponseBody String checkEmail(@RequestParam(value = "email") String email) {
    	
    	System.out.println("checking email: " + email);

    	//Check if there are any users with the given email
    	if (userRepository.findByEmail(email).size() > 0)
    		return "true";
    	else
    		return "false";
    }
    
    @PostMapping("/remove")
    public @ResponseBody String removeUser(@RequestParam(value = "id") int userID) {

    	System.out.println("removing user: " + userID);
    	
    	if (userRepository.findById(userID) != null) {
    		
    		userRepository.deleteById(userID);            
            return "true";    		
    	}
    	else
    		return "false";
    	
    }
}
