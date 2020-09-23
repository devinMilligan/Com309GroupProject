package com.example.project309.app;

import android.media.Image;

import java.util.ArrayList;

public class Profile {

    public static ArrayList<Profile> allProfiles= new ArrayList<>();

    protected String userName;
    protected String name;
    protected Image imProf;
    protected int id;
    protected int accountType;
    protected String password;

    public Profile(int mId, String user, String pass, int acctType, String n){

        id = mId;
        accountType = acctType; // 1 = Customer/Deliverer, 2 = Dining, 3 = Admin
        userName = user;
        password = pass;
        name = n;

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
    public String getPassword(){
        return password;
    }
    public int getId(){
        return id;
    }
    public int getAccountType() { return accountType; }
    public String getName() { return name; }

    public void setImage(Image im){
        imProf = im;
    }
    public void setUserName(String user){
        userName = user;
    }
    public void setPassword(String pass){
        password = pass;
    }
    public void setAccountType(int acctType) { accountType = acctType; }
    public void setName(String n) { name = n; }

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
