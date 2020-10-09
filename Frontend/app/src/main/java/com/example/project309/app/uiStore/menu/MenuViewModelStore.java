package com.example.project309.app.uiStore.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.MenuItem;
import com.example.project309.app.Order;

import java.util.ArrayList;

public class MenuViewModelStore extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<MenuItem>> menuItems;

    public MenuViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Menu");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<MenuItem>> getMenuItems(){

        if(menuItems == null){

            menuItems = new MutableLiveData<ArrayList<MenuItem>>();

            loadMenu();

        }
        return menuItems;

    }

    private void loadMenu(){

        ArrayList<MenuItem> aMenuItems = new ArrayList<>();
        aMenuItems.add(new MenuItem("Menu Item 1", 9.99));
        aMenuItems.add(new MenuItem("Menu Item 2", 9.99));

        menuItems.setValue(aMenuItems);

    }

}