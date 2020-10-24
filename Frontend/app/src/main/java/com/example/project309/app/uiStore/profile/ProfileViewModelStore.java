package com.example.project309.app.uiStore.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Profile;
import com.example.project309.app.Store;

public class ProfileViewModelStore extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Store> store;

    public ProfileViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Store Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Store> getStore(){

        store = new MutableLiveData<>();

        store.setValue(Store.currentStore);

        return store;

    }
}