package com.example.project309.app;

import com.example.project309.app.Profile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sets up and stores information specific to an admin profile
 *
 * @author Ryan Hickok
 */
public class AdminProfile extends Profile {
    /**
     * Admin first name
     */
    protected String firstName;
    /**
     * Admin last name
     */
    protected String lastName;

    /**
     * Default Constructor
     */
    public AdminProfile() {
        super();

        firstName = "";
        lastName = "";
    }

    /**
     * Sets up an admin profile based on the information provided
     * @param mId
     * @param userName
     * @param pass
     * @param first
     * @param last
     */
    public AdminProfile(int mId, String userName, String pass, String first, String last) {
        super(mId, userName, pass, AccountType.ADMIN_ACCOUNT, first + " " + last);

        firstName = first;
        lastName = last;

    }

    /**
     * Sets up an admin profile based on a pre-made id number
     * @param mId
     */
    public AdminProfile(int mId) {
        super(mId);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    /**
     * Changes the admin's first name and full name in the Profile class
     *
     * @param first new first name
     */
    public void setFirstName(String first) {
        firstName = first;
        if(lastName != null && !lastName.isEmpty())
            name = firstName + " " + lastName;
        else
            name = firstName;
    }

    /**
     * Changes the admin's last name and full name in the Profile class
     *
     * @param last new last name
     */
    public void setLastName(String last) {
        lastName = last;
        if(firstName != null && !firstName.isEmpty())
            name = firstName + " " + lastName;
        else
            name = lastName;
    }

    /**
     * Adds a new account to the system based on provided information
     *
     * @param mId
     * @param user
     * @param pass
     * @param acctType
     * @param n
     */
    public void addAccount(int mId, String user, String pass, AccountType acctType, String n) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            Profile p = new Profile(mId, user, pass, acctType, n);
        }
    }

    /**
     * Adds a new account to the system with just an id number
     * @param mId
     */
    public void addAccount(int mId) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            Profile p = new Profile(mId);
        }
    }

    /**
     * Returns a new CustomerDeliverer object containing the information provided
     *
     * @param info JSON Object with profile information to return
     * @return Admin profile object with information provided by JSON Object
     */
    public static AdminProfile getProfileInfo(JSONObject info) {
        AdminProfile p = new AdminProfile();

        try {
            p.setEmail(info.get("email").toString());
            p.setFirstName(info.get("firstName").toString());
            p.setLastName(info.get("lastName").toString());
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

    /*public void removeAccount(int mId) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            for(int i = 0; i < allProfiles.size(); i++)
            {
                if(allProfiles.get(i).getId() == mId) {
                    allProfiles.remove(i);
                    break;
                }
            }
        }
    }
    public void setAccountTypeAdmin(int mId, AccountType acctType) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            for(int i = 0; i < allProfiles.size(); i++)
            {
                if(allProfiles.get(i).getId() == mId) {
                    allProfiles.get(i).setAccountType(acctType);
                    break;
                }
            }
        }
    }

    public void setStore(int mId, String name, String location) {
        for(int i = 0; i < allProfiles.size(); i++)
        {
            if(allProfiles.get(i).getId() == mId)
            {
                if(allProfiles.get(i).getAccountType() == AccountType.DINING_ACCOUNT)
                {
                    ((DiningProfile)(allProfiles.get(i))).setStore(name, location);
                }
                break;
            }
        }
    }*/
}
