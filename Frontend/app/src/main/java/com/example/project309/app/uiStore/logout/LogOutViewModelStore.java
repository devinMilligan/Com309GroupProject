package com.example.project309.app.uiStore.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogOutViewModelStore extends ViewModel {

    private MutableLiveData<String> mText;

    public LogOutViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("This is log out fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}