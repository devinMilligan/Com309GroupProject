package com.example.project309.app.ui.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MapModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}