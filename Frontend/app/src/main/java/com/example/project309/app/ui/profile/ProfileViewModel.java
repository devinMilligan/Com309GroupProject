package com.example.project309.app.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Profile;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Profile> currentProfile;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Profile> getCurrentProfile(){

        currentProfile = new MutableLiveData<>();

        currentProfile.setValue(Profile.currentLogin);

        return currentProfile;

    }
}