package com.example.project309.app;

import android.media.Image;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sets up and stores general information about a user profile
 *
 * @author Ryan Hickok
 */
public class Profile {

    /**
     * Profile of user that is currently logged in
     */
    public static Profile currentLogin;
    /**
     * User email
     */
    protected String email;
    /**
     * User full name
     */
    protected String name;
    /**
     * User profile image
     */
    protected Image imProf;
    /**
     * User id number
     */
    protected int id;
    /**
     * User account type
     */
    protected AccountType accountType;
    /**
     * User password
     */
    protected String password;

    /**
     * Default Constructor
     */
    public Profile(){
        id = -1;
        accountType = null;
        email = "";
        password = "";
        name = "";
    }

    /**
     * Constructor to set up user profile
     *
     * @param mId
     * @param user
     * @param pass
     * @param acctType
     * @param n
     */
    public Profile(int mId, String user, String pass, AccountType acctType, String n){

        id = mId;
        accountType = acctType;
        email = user;
        password = pass;
        name = n;
    }

    /**
     * Constructor that utilizes a pre-made id number
     * @param mId
     */
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
    public AccountType getAccountType() {
        return accountType;
    }
    public String getName() {
        return name;
    }

    public void setImage(Image im){ imProf = im; }
    public void setId(int i) { id = i;}
    public void setEmail(String e){ email = e; }
    public void setPassword(String pass){ password = pass; }
    public void setName(String n) { name = n; }
    public void setAccountType(AccountType acctType) { accountType = acctType; }

    /**
     * Sets the account type based on a string input
     * @param s New account type
     */
    public void setAccountType(String s) {
        if(s.equalsIgnoreCase("CustomerDeliverer"))
        {
            accountType = AccountType.CUSTOMER_DELIVERER_ACCOUNT;
        }
        else if(s.equalsIgnoreCase("Manager"))
        {
            accountType = AccountType.MANAGER_ACCOUNT;
        }
        else if(s.equalsIgnoreCase("Admin"))
        {
            accountType = AccountType.ADMIN_ACCOUNT;
        }
        else
        {
            accountType = null;
        }
    }

    /**
     * Equals method for a profile object
     *
     * @param o Object to compare to
     * @return true if objects are equal
     */
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

    /**
     * Returns a new Profile object containing the information provided
     *
     * @param info JSON Object with profile information to return
     * @return Profile with information provided by JSON Object
     */
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
        catch (NullPointerException n) {
            return null;
        }

        return p;
    }

}