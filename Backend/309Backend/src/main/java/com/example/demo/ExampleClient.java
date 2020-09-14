package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleClient {
	
  @Autowired
  private UserDAO dao;

  public void run() {
      User person = User.create("Dana", "abc123#");
      System.out.println("saving person: " + person);
      dao.registerUser(person);

      person = User.create("Robin", "hello1!");
      System.out.println("saving person: " + person);
      dao.registerUser(person);

      System.out.println("-- loading all --");
      List<User> persons = dao.getUsers();
      persons.forEach(System.out::println);
  }
}