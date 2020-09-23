package com.example.project309.app;

import com.example.project309.app.Profile;

public class AdminProfile extends Profile {
    protected String firstName;
    protected String lastName;

    public AdminProfile(int mId, String userName, String pass, String first, String last) {
        super(mId, userName, pass, 3, first + " " + last);

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



    public void addAccount(int mId, String user, String pass, int acctType, String n) {
        if(this.accountType == 3)
        {
            Profile p = new Profile(mId, user, pass, acctType, n);
        }
    }
    public void addAccount(int mId) {
        if(this.accountType == 3)
        {
            Profile p = new Profile(mId);
        }
    }

    public void removeAccount(int mId) {
        if(this.accountType == 3)
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
    public void setAccountTypeAdmin(int mId, int acctType) {
        if(this.accountType == 3)
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
                if(allProfiles.get(i).getAccountType() == 2)
                {
                    ((DiningProfile)(allProfiles.get(i))).setStore(name, location);
                }
                break;
            }
        }
    }
}
