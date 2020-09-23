package com.example.project309.app;

import com.example.project309.app.Profile;

public class CustomerDelivererProfile extends Profile {
    protected String address;
    protected String firstName;
    protected String lastName;
    protected boolean deliveryStatus;

    public CustomerDelivererProfile(int mId, String userName, String first, String last, String pass, String add, boolean delivery) {
        super(mId, userName, pass, 1, first + last);

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
    }
    public void setLastName(String last) {
        lastName = last;
    }
    public void setDeliveryStatus(boolean delivery) {
        deliveryStatus = delivery;
    }
}
