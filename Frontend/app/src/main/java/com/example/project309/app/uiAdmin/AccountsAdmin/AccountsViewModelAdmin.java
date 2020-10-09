package com.example.project309.app.uiAdmin.AccountsAdmin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Profile;

import java.util.ArrayList;

public class AccountsViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Profile>> adminAccts;

    public AccountsViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Admin Accounts");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<Profile>> getAdminAccts(){

        if(adminAccts == null){

            adminAccts = new MutableLiveData<ArrayList<Profile>>();

            loadAdminAccounts();

        }
        return adminAccts;

    }

    private void loadAdminAccounts(){

        ArrayList<Profile> aProfile = new ArrayList<>();
        aProfile.add(new Profile(1, "username", "password", 1, "Devin"));
        aProfile.add(new Profile(1, "username", "password", 1, "Ryan"));

        adminAccts.setValue(aProfile);

    }

}