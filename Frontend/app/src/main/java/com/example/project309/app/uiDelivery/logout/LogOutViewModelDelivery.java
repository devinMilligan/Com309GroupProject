package com.example.project309.app.uiDelivery.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogOutViewModelDelivery extends ViewModel {

    private MutableLiveData<String> mText;

    public LogOutViewModelDelivery() {
        mText = new MutableLiveData<>();
        mText.setValue("This is log out fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}