package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application{


	  public static void main(String[] args) {
	      SpringApplication sa = new SpringApplication(Application.class);


		  ExampleClient exampleClient = new ExampleClient();
		  exampleClient.run();
	      sa.run(args);
	  }
	  
	  public void run(String... args) {
	  }

}
