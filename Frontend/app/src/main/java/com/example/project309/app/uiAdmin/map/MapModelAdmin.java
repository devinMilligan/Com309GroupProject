package com.example.project309.app.uiAdmin.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapModelAdmin extends ViewModel {

    private MutableLiveData<String> mText;

    public MapModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}