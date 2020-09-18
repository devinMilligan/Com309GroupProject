package com.project.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class registerController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register()
	{
		return null;		
	}
	
}
