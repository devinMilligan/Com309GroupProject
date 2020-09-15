package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application{

	@Autowired
	ExampleClient exampleClient;
	
	public static void main(String[] args) {
	    SpringApplication sa = new SpringApplication(Application.class);
	    sa.run(args);
	}
	  
	public void run(String[] args) {
		exampleClient.run();
	}
}
