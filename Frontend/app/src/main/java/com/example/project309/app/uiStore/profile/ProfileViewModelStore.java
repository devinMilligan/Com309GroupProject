package com.example.project309.app.uiStore.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModelStore extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Profile Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}