package com.example.project309.app.ui.switchuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwitchUserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SwitchUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is switch user fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}