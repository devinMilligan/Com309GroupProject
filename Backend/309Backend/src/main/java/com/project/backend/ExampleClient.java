package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ExampleClient {
    @Autowired
    private UserDao dao;

    public void run() {
        User user = User.create("Dana", "Whitley", "464 Yellow Drive");
        System.out.println("saving user: " + user);
        dao.save(user);

        user = User.create("Robin", "Cash", "64 Logic Park");
        System.out.println("saving user: " + user);
        dao.save(user);

        System.out.println("-- loading all --");
        List<User> users = dao.loadAll();
        users.forEach(System.out::println);
    }
}