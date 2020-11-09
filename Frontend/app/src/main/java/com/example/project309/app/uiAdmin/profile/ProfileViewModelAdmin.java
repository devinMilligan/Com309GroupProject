package com.example.project309.app.uiAdmin.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Profile;

/**
 * This class holds the describing text of the fragment and the current user
 *
 * @author Devin Milligan
 */
public class ProfileViewModelAdmin extends ViewModel {

    /**
     * Holds the current describing text of the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Holds the {@link Profile} instance that pertains to the current user
     */
    private MutableLiveData<Profile> currentProfile;

    /**
     * Default constructor sets the current describing text for the fragement
     */
    public ProfileViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData instance holding the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Returns the current user that is logged in
     *
     * @return LiveData instance contating the current user as a {@link Profile}
     */
    public LiveData<Profile> getCurrentProfile(){

        currentProfile = new MutableLiveData<>();

        currentProfile.setValue(Profile.currentLogin);

        return currentProfile;

    }
}