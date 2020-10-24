package com.example.project309.app;

import com.example.project309.app.Profile;

public class DiningProfile extends Profile {
    protected Store store;

    public DiningProfile(int mId, String userName, String pass, String location, String name) {
        super(mId, userName, pass, 2, name);
        store = new Store(name, location);
    }

    public DiningProfile(int mId) {
        super(mId);
    }

    public String getName() {
        return store.getName();
    }
    public String getAddress() {
        return store.getAddress();
    }

    public void setName(String name) {
        store.setName(name);
    }
    public void setLocation(String loc) {
        store.setAddress(loc);
    }
    public void setStore(String n, String loc) {
        store.setName(n);
        store.setAddress(loc);
    }
}
