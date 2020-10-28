package com.example.project309.app;

import com.example.project309.app.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class DiningProfile extends Profile {
    protected Store store;

    public DiningProfile() {
        super();

        store = null;
    }

    public DiningProfile(int mId, String userName, String pass, String location, String name) {
        super(mId, userName, pass, AccountType.DINING_ACCOUNT, name);
        store = new Store(name, location);
    }

    public DiningProfile(int mId) {
        super(mId);
    }

    public String getName() {
        return store.getName();
    }
    public String getLocation() {
        return store.getLocation();
    }

    public void setName(String name) {
        store.setName(name);
    }
    public void setLocation(String loc) {
        store.setLocation(loc);
    }
    public void setStore(String n, String loc) {
        store.setName(n);
        store.setLocation(loc);
    }

    public static DiningProfile getProfileInfo(JSONObject info) {
        DiningProfile p = new DiningProfile();

        try {
            p.setEmail(info.get("email").toString());
            p.setName(info.get("firstName").toString());
            p.setLocation(info.get("address").toString());
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
