package com.example.project309.app;

import android.media.Image;

import java.util.ArrayList;

public class Profile {

    public static ArrayList<Profile> allProfiles= new ArrayList<>();

    protected String userName;
    protected Image imProf;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected int id;
    protected String password;

    public Profile(int mId, String userName, String first, String last, String pass, String add){

        id = mId;
        firstName = first;
        lastName = last;
        password = pass;
        address = add;

        if(!alreadyContains()) {
            allProfiles.add(this);
        }

    }
    public Profile(int mId){

        id = mId;
        if(!alreadyContains()) {
            allProfiles.add(this);
        }

    }

    public Image getImage(){
        return imProf;
    }
    public String getUserName(){
        return userName;
    }
    public String getName(){
        return firstName + " " + lastName;
    }
    public String getAddress(){
        return address;
    }
    public String getPassword(){
        return password;
    }
    public int getId(){
        return id;
    }

    public void setImage(Image im){
        imProf = im;
    }
    public void setUserName(String user){
        userName = user;
    }
    public void setFirstName(String first){
        firstName = first;
    }
    public void setLastName(String last){
        lastName =last;
    }
    public void setAddress(String add){
        address = add;
    }
    public void setPassword(String pass){
        password = pass;
    }

    private boolean alreadyContains(){

        if(allProfiles.contains(this)){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){

        if(o instanceof Profile){

            Profile temp = (Profile)o;

            if(temp.getId() == this.id){
                return true;
            }
        }

        return false;
    }




}
