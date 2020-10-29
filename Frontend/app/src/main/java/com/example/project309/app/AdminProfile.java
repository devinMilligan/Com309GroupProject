package com.example.project309.app;

import com.example.project309.app.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminProfile extends Profile {
    protected String firstName;
    protected String lastName;

    public AdminProfile() {
        super();

        firstName = "";
        lastName = "";
    }

    public AdminProfile(int mId, String userName, String pass, String first, String last) {
        super(mId, userName, pass, AccountType.ADMIN_ACCOUNT, first + " " + last);

        firstName = first;
        lastName = last;

    }

    public AdminProfile(int mId) {
        super(mId);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String first) {
        firstName = first;
        if(lastName != null && !lastName.isEmpty())
            name = firstName + " " + lastName;
        else
            name = firstName;
    }
    public void setLastName(String last) {
        lastName = last;
        if(firstName != null && !firstName.isEmpty())
            name = firstName + " " + lastName;
        else
            name = lastName;
    }



    public void addAccount(int mId, String user, String pass, AccountType acctType, String n) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            Profile p = new Profile(mId, user, pass, acctType, n);
        }
    }
    public void addAccount(int mId) {
        if(this.accountType == AccountType.ADMIN_ACCOUNT)
        {
            Profile p = new Profile(mId);
        }
    }

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
