package com.example.project309.app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Store;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Store>> stores;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<Store>> getStores(){

        if(stores == null){

            stores = new MutableLiveData<ArrayList<Store>>();

            loadStores();

        }
        return stores;

    }

    private void loadStores(){

        ArrayList<Store> aStore = new ArrayList<>();
        aStore.add(new Store("Seasons Dining Center", "224 Beach Rd, Ames, IA 50011"));
        aStore.add(new Store("Conversations", "1215 Richardson Ct, Ames, IA 50012"));

        stores.setValue(aStore);

    }

}