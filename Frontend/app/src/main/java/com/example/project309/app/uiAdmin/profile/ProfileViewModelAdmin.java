package com.example.project309.app.uiAdmin.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}