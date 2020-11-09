package com.example.project309.app.uiStore.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.MenuItem;
import com.example.project309.app.Order;

import java.util.ArrayList;

/**
 * This class gets the menu for the store from the server and holds the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class MenuViewModelStore extends ViewModel {

    /**
     * Holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * List of {@link MenuItem}s that is the current list of the menu for the store
     */
    private MutableLiveData<ArrayList<MenuItem>> menuItems;

    /**
     * Default Constructor that sets the describing text for the fragment
     */
    public MenuViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Menu");
    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData instance contating the text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the menuitems have been retrieved yet and calls to get them if not and returns them
     *
     * @return LiveData arraylist of {@link MenuItem}s that are related to this store
     */
    public LiveData<ArrayList<MenuItem>> getMenuItems(){

        if(menuItems == null){

            menuItems = new MutableLiveData<ArrayList<MenuItem>>();

            loadMenu();

        }
        return menuItems;

    }

    /**
     * Gets the menu for this store from the server
     */
    private void loadMenu(){

        ArrayList<MenuItem> aMenuItems = new ArrayList<>();
        aMenuItems.add(new MenuItem("Menu Item 1","Description", 9.99));
        aMenuItems.add(new MenuItem("Menu Item 2", "Description",9.99));

        menuItems.setValue(aMenuItems);

    }

}