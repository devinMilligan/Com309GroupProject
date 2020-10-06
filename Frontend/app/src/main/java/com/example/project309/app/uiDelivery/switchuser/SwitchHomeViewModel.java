package com.example.project309.app.uiDelivery.switchuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwitchHomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SwitchHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is switch home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}