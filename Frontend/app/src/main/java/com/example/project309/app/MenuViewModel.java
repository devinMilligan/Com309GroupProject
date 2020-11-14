package com.example.project309.app;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MenuViewModel extends ViewModel implements ViewListenerInter {
    // TODO: Implement the ViewModel

    private MutableLiveData<ArrayList<MenuItem>> items;

    private JSONHandlerInter json;

    private Menu currentMenu;

    public MenuViewModel(){

        json = new JSONHandler();
        json.setListener(this);

    }

    public LiveData<ArrayList<MenuItem>> getMenuItems(){

        items = new MutableLiveData<ArrayList<MenuItem>>();
        if(Store.currentStore.getMenu() == null || Store.currentStore.getMenu().getMenuItems().size() == 0){

            loadItems();

        }else{

            items.setValue(Store.currentStore.getMenu().getMenuItems());

        }
        return items;

    }

    private void loadItems(){

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

        items.setValue(currentMenu.getMenuItems());

        MenuFragment.updateView(items.getValue());

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

        Log.d("MENU", "Error: " + error.toString());

    }
}