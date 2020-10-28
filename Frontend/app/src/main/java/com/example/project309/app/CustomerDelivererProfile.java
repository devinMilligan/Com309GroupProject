package com.example.project309.app;

import com.example.project309.app.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerDelivererProfile extends Profile {
    protected String address;
    protected String firstName;
    protected String lastName;
    protected boolean deliveryStatus;

    public CustomerDelivererProfile() {
        super();

        address = "";
        firstName = "";
        lastName = "";
        deliveryStatus = false;
    }

    public CustomerDelivererProfile(int mId, String userName, String first, String last, String pass, String add, boolean delivery) {
        super(mId, userName, pass, AccountType.CUSTOMER_DELIVERER_ACCOUNT, first + " " + last);

        address = add;
        firstName = first;
        lastName = last;
        deliveryStatus = delivery;
    }

    public CustomerDelivererProfile(int mId) {
        super(mId);
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
    public boolean getDeliveryStatus() {
        return deliveryStatus;
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
    public void setDeliveryStatus(boolean delivery) {
        deliveryStatus = delivery;
    }

    public static CustomerDelivererProfile getProfileInfo(JSONObject info) {
        CustomerDelivererProfile p = new CustomerDelivererProfile();

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
