package com.example.project309.app;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class is the Profile of a manager
 *
 * @author Devin Milligan
 */
public class ManagerProfile extends Profile {

    /**
     * List of all managers from the server
     */
    public static ArrayList<ManagerProfile> managers = new ArrayList<>();

    protected String address;
    protected String firstName;
    protected String lastName;

    public ManagerProfile() {
        super();
    }

    public ManagerProfile(int mId, String userName, String pass, String name) {
        super(mId, userName, pass, AccountType.MANAGER_ACCOUNT, name);

    }

    public ManagerProfile(int mId) {
        super(mId);
    }

    public static void addManagerToList(ManagerProfile prof){
        managers.add(prof);
    }

    public String getAddress() {
        return address;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setAddress(String add) {
        address = add;
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

    /**
     * Used to get a ManagerProfile instance from a JSONObject
     *
     * @param info JSONObject that holds a ManagerProfile in json format
     * @return
     */
    public static ManagerProfile getProfileInfo(JSONObject info) {
        ManagerProfile p = new ManagerProfile();

        try {
            p.setEmail(info.get("email").toString());
            p.setAddress(info.get("address").toString());
            p.setFirstName(info.get("firstName").toString());
            p.setLastName(info.get("lastName").toString());
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
