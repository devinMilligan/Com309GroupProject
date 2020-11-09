package com.example.project309.app.uiAdmin.home;

import android.util.Log;

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

/**
 * This class holds the list of all stores and gets them from the server if it does not hold them already
 * Also holds the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class HomeViewModelAdmin extends ViewModel implements ViewListenerInter {

    /**
     * Holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * Holds the list of {@link Store}s to be retrieved by the fragment
     */
    private MutableLiveData<ArrayList<Store>> stores;
    /**
     * {@link JSONHandler} instance to be used to make a request to the server
     */
    private static JSONHandlerInter jsonH;


    /**
     * Default constructor that sets the describing text and initialize the {@link JSONHandler}
     */
    public HomeViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);



    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData<String> instance contating the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the list of stores has already been collected and then calls to get it if not
     * and sets the local list of stores to the list of complete stores
     *
     * @return LiveData instance containing the list of all the {@link Store}s
     */
    public LiveData<ArrayList<Store>> getStores(){

        if(stores == null){
            stores = new MutableLiveData<>();
            if(Store.allStores.isEmpty()) {
                loadStores();
            }else{
                stores.setValue(Store.allStores);
            }

        }
        return stores;

    }

    /**
     * Makes a request to the server for the list of all the stores
     */
    public static void loadStores(){

        jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);

    }

    /**
     * Recieves the response from the server as a JSONObject and creates a {@link Store} instance from it
     * and adds it to the list of complete stores
     *
     * @param response JSONObject containg the {@link Store} in JSON form
     */
    @Override
    public void onSuccess(JSONObject response) {

        Store s = Store.getStore(response);
        Store.addStoreToList(s);

    }

    /**
     * Receives the array of JSONObjects with the {@link Store}s in JSON form and turns them into useable
     * instances and sets the local list of stores to the master list
     *
     * @param response JSONArray instance contating all the json form store objects
     */
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

    /**
     * Handles a server request error
     *
     * @param error error returned from server request
     */
    @Override
    public void onError(VolleyError error) {

        Log.d("HOMEVIEWADMIN", error.toString());

    }

}