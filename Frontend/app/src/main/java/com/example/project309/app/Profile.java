package com.example.project309.app;

import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile {

    public static Profile currentLogin;

    protected String email;
    protected String name;
    protected Image imProf;
    protected int id;
    protected AccountType accountType;
    protected String password;

    public Profile(){
        id = -1;
        accountType = null;
        email = "";
        password = "";
        name = "";
    }

    public Profile(int mId, String user, String pass, AccountType acctType, String n){

        id = mId;
        accountType = acctType;
        email = user;
        password = pass;
        name = n;
    }
    public Profile(int mId){

        id = mId;
        accountType = null;
        email = "";
        password = "";
        name = "";
    }

    public Image getImage(){
        return imProf;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public int getId(){
        return id;
    }
    public AccountType getAccountType() { return accountType; }
    public String getName() { return name; }

    public void setImage(Image im){ imProf = im; }
    public void setId(int i) { id = i;}
    public void setEmail(String e){
        email = e;
    }
    public void setPassword(String pass){ password = pass; }
    public void setName(String n) { name = n; }
    public void setAccountType(AccountType acctType) { accountType = acctType; }
    public void setAccountType(String s) {
        if(s.equalsIgnoreCase("CustomerDeliverer"))
        {
            accountType = AccountType.CUSTOMER_DELIVERER_ACCOUNT;
        }
        else if(s.equalsIgnoreCase("Dining"))
        {
            accountType = AccountType.DINING_ACCOUNT;
        }
        else if(s.equalsIgnoreCase("Admin"))
        {
            accountType = AccountType.ADMIN_ACCOUNT;
        }
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

    public static Profile getProfileInfo(JSONObject info) {
        Profile p = new Profile();

        try {
            p.setEmail(info.get("email").toString());
            p.setName(info.get("firstName").toString() + " " + info.get("lastName").toString());
            p.setId(Integer.parseInt(info.get("id").toString()));
            p.setPassword(info.get("password").toString());
            p.setAccountType(info.get("type").toString());
        }
        catch (JSONException e) {
            return null;
        }

        return p;
    }

}