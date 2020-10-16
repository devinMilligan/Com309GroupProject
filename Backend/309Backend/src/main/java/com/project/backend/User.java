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
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String account;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return account;
    }

    public void setType(String acctType) {
        this.account = acctType;
    }

    public String getImagePath() {
        return image;
    }

    public void setImagePath(String imagePath) {
        this.image = imagePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", account='" + account + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
    
    public User()
    {
    	this.id = (0);
    	this.email = (null);
    	this.password = (null);
    	this.firstName = (null);
    	this.lastName = (null);
    	this.address = (null);
    	this.account = (null);
    	this.image = (null);
    }
    
    public User(int id, String email, String password, String address, String firstName, String lastName, String acctType, String imagePath)
    {
    	this.id = (id);
    	this.email = (email);
    	this.password = (password);
    	this.firstName = (firstName);
    	this.lastName = (lastName);
    	this.address = (address);
    	this.account = (acctType);
    	this.image = (imagePath);
    }
}