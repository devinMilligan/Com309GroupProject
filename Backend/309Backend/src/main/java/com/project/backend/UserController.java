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
	
    @PostMapping("/updateUser")
    public @ResponseBody User UpdateUser(@RequestParam(value = "id") int id, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
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
    
    @PostMapping("/update")
    public @ResponseBody User Update(@RequestBody User UserDetails) {
        User input = new User();
        input.setId(UserDetails.getId());
        input.setEmail(UserDetails.getEmail());
        input.setPassword(UserDetails.getPassword());
        input.setFirstName(UserDetails.getFirstName());
        input.setLastName(UserDetails.getLastName());
        input.setAddress(UserDetails.getAddress());
        input.setType(UserDetails.getType());
        input.setImagePath(UserDetails.getImagePath());

    	System.out.println("updating user: " + input.getId());
    	userRepository.save(input);
    	return input;
    }

    @PostMapping("/newUser")
    public @ResponseBody User NewUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
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

    	if (checkEmail(user.getEmail()))
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
    
    @PostMapping("/new")
    public @ResponseBody User createUser(@RequestBody User UserDetails) {
        User input = new User();
        input.setEmail(UserDetails.getEmail());
        input.setPassword(UserDetails.getPassword());
        input.setFirstName(UserDetails.getFirstName());
        input.setLastName(UserDetails.getLastName());
        input.setAddress(UserDetails.getAddress());
        input.setType(UserDetails.getType());
        input.setImagePath(UserDetails.getImagePath());

    	if (checkEmail(input.getEmail()))
    	{
        	System.out.println("Email already exists in database");
        	return null;
    	}
    	else
    	{
        	System.out.println("saving user: " + input);
        	userRepository.save(input);
        	return input;
    	}
    }
    
    @GetMapping("/all")
    public @ResponseBody List<User> getAllUsers() {

    	System.out.println("-- loading all --");
    	List<User> users = new ArrayList<User>();
    	for (User user : userRepository.findAll()) {
            users.add(user);
        }
        users.forEach(System.out::println);
        return users;
    }
    
    @GetMapping("/search")
    public @ResponseBody List<User> searchUsers(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
    	
    	System.out.println("-- searching users --");
        List<User> result = new ArrayList<User>();
   	 	for (User user : userRepository.findByEmailAndPassword(email, password)) {
   	 		result.add(user);
   	 	}
        result.forEach(System.out::println);
        return result;
    }
    
    @GetMapping("/checkEmail")
    public @ResponseBody Boolean checkEmail(@RequestParam(value = "email") String email) {
    	
    	System.out.println("checking email: " + email);
    	
    	if (userRepository.findByEmail(email).size() > 0)
    		return true;
    	else
    		return false;
    }
}
