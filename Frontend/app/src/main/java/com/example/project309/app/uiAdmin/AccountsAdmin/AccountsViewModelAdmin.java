package com.example.project309.app.uiAdmin.AccountsAdmin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.AccountType;
import com.example.project309.app.Profile;

import java.util.ArrayList;

/**
 * This class gets the Admin Accounts from the server and stores them for later use
 *
 * @author Devin Milligan
 */
public class AccountsViewModelAdmin extends ViewModel {

    /**
     * Holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * Holds the list of the {@link Profile}s that are the Admin Accounts
     */
    private MutableLiveData<ArrayList<Profile>> adminAccts;

    /**
     * Default Constructor that sets the describing text
     */
    public AccountsViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Admin Accounts");
    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData<String> that contains the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Checks to see if the Admin Accounts have been collected and if not calls to collect them
     *
     * @return LiveData<ArrayList<Profile> instance that contains the list of admin accounts as {@link Profile}s
     */
    public LiveData<ArrayList<Profile>> getAdminAccts(){

        if(adminAccts == null){

            adminAccts = new MutableLiveData<ArrayList<Profile>>();

            loadAdminAccounts();

        }
        return adminAccts;

    }

    /**
     * Gets the Admin Accounts from the server and stores them in the list to be stored
     */
    private void loadAdminAccounts(){

        ArrayList<Profile> aProfile = new ArrayList<>();
        aProfile.add(new Profile(1, "username", "password", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "Devin"));
        aProfile.add(new Profile(1, "username", "password", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "Ryan"));

        adminAccts.setValue(aProfile);

    }

}