package com.example.project309.app.uiStore.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapModelStore extends ViewModel {

    private MutableLiveData<String> mText;

    public MapModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}