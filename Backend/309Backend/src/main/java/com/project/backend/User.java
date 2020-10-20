package com.project.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//ID property is unique and automatically generated
    private Integer id;
	
	//email and password values will be used to log the user into the app
    private String email;
    private String password;
    
    //Name and address properties are used to identify delivery users to ordering users, and vice versa
    private String firstName;
    private String lastName;
    private String address;
    
    //Account type is used to determine which screens in the app will be displayed to the user
    //Options are Orderer, Deliverer, and Manager
    private String account;
    
    //Image property is used to store the path to a profile image located on the user's device
    //The image will be copied to the app's file, and displayed in their app
    private String image;

    //Getter and setter for User ID property
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    //Getter and setter for User Email property
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    //Getter and setter for User Password Property
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Getters and setters for User's Names properties
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Getter and setter for User Address property
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    //Getter and setter for User Account Type property
    public String getType() {
        return account;
    }
    public void setType(String acctType) {
        this.account = acctType;
    }

    //Getter and setter for User Profile Image filepath property
    public String getImagePath() {
        return image;
    }
    public void setImagePath(String imagePath) {
        this.image = imagePath;
    }
}