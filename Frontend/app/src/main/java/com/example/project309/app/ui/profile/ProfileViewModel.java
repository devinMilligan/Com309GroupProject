package com.example.project309.app.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Profile;

/**
 * Stores and gets the live current value of the user and the describing text
 *
 * @author Devin Miligan
 */
public class ProfileViewModel extends ViewModel {

    /**
     * The describing text of the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Holds the {@link Profile} instance that pertains to the current user
     */
    private MutableLiveData<Profile> currentProfile;

    /**
     * Defualt constructor that sets the describing text
     */
    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData<String> instance that holds the text value
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Returns the current user that is logged in
     *
     * @return LiveData<Profile> contating the current user
     */
    public LiveData<Profile> getCurrentProfile(){

        currentProfile = new MutableLiveData<>();

        currentProfile.setValue(Profile.currentLogin);

        return currentProfile;

    }
}