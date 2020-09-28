package com.example.project309.app.uiAdmin.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogOutViewModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public LogOutViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is log out fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}