package com.example.project309.app;

import com.example.project309.app.Profile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sets up and stores information specific to a customer/deliverer profile
 *
 * @author Ryan Hickok
 */
public class CustomerDelivererProfile extends Profile {

    /**
     * User address
     */
    protected String address;
    /**
     * User first name
     */
    protected String firstName;
    /**
     * User last name
     */
    protected String lastName;
    /**
     * Distinguishes if a user is a deliverer or customer
     */
    protected boolean deliveryStatus;

    /**
     * Default Constructor
     */
    public CustomerDelivererProfile() {
        super();

        address = "";
        firstName = "";
        lastName = "";
        deliveryStatus = false;
    }

    /**
     * Sets up a customer/deliverer profile based on the provided information
     *
     * @param mId
     * @param userName
     * @param first
     * @param last
     * @param pass
     * @param add
     * @param delivery
     */
    public CustomerDelivererProfile(int mId, String userName, String first, String last, String pass, String add, boolean delivery) {
        super(mId, userName, pass, AccountType.CUSTOMER_DELIVERER_ACCOUNT, first + " " + last);

        address = add;
        firstName = first;
        lastName = last;
        deliveryStatus = delivery;
    }

    /**
     * Sets up a customer/deliverer profile based on a pre-made id number
     *
     * @param mId
     */
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

    /**
     * Changes the user's first name and full name in the Profile class
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
     * Changes the user's last name and full name in the Profile class
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
    public void setDeliveryStatus(boolean delivery) {
        deliveryStatus = delivery;
    }

    /**
     * Returns a new CustomerDeliverer object containing the information provided
     *
     * @param info JSON Object with profile information to return
     * @return CustomerDeliverer profile object with information provided by JSON Object
     */
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
        catch (NullPointerException n) {
            return null;
        }

        return p;
    }
}
