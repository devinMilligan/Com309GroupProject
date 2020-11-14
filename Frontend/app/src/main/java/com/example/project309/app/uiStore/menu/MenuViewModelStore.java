package com.example.project309.app.uiStore.menu;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.Menu;
import com.example.project309.app.MenuItem;
import com.example.project309.app.Order;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class gets the menu for the store from the server and holds the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class MenuViewModelStore extends ViewModel implements ViewListenerInter {

    /**
     * Holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * List of {@link MenuItem}s that is the current list of the menu for the store
     */
    private MutableLiveData<ArrayList<MenuItem>> menuItems;

    private JSONHandlerInter json;

    private Menu currentMenu;

    /**
     * Default Constructor that sets the describing text for the fragment
     */
    public MenuViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Menu");

        json = new JSONHandler();
        json.setListener(this);

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

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("store",Integer.toString(Store.currentStore.getID())));

        json.makeJsonArryReqParams(Const.URL_JSON_GET_STORE_MENU,params);

    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onSuccess(JSONArray response) {

        currentMenu = Menu.getMenuJSON(response);
        Store.currentStore.setMenu(currentMenu);

        menuItems.setValue(currentMenu.getMenuItems());

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

        Log.d("MENUSTORE", "Error: " + error.toString());

    }
}