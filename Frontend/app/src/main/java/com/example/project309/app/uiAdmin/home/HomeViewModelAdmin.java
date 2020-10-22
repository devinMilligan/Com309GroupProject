package com.example.project309.app.uiAdmin.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.AppController;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeViewModelAdmin extends ViewModel implements ViewListenerInter {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Store>> stores;
    private static JSONHandlerInter jsonH;

    public HomeViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        stores = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<Store>> getStores(){

        if(stores == null){

            if(Store.allStores.isEmpty()) {
                loadStores();
            }else{
                stores.setValue(Store.allStores);
            }

        }
        return stores;

    }

    public static void loadStores(){

        jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);

    }

    @Override
    public void onSuccess(JSONObject response) {

        /*

        Store s = Store.getProfile(response);
        Store.addStoreToList(s);

         */

    }

    @Override
    public void onSuccess(JSONArray response) {

        for(int i = 0; i<response.length();i++){

            try {
                onSuccess(response.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        stores.setValue(Store.allStores);


    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }

}