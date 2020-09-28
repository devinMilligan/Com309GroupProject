package com.example.project309.app.uiDelivery.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapModelDelivery extends ViewModel {

    private MutableLiveData<String> mText;

    public MapModelDelivery() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}