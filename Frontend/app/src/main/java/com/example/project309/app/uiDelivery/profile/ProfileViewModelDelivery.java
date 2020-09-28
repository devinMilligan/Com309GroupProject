package com.example.project309.app.uiDelivery.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelDelivery extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelDelivery() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}