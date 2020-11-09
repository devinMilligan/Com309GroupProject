package com.example.project309.app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.AppController;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONRequestInter;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * HomeViewModel checks if stores need to be pulled from the database or if this has already been done.
 * This allows for the ability to not pull the list of stores every time.
 *
 * @author Devin Milligan
 */
public class HomeViewModel extends ViewModel implements ViewListenerInter {

    /**
     * Contains the text that will be used to describe the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Contains the live list of {@link Store}s that updates with the complete list of
     */
    private MutableLiveData<ArrayList<Store>> stores;

    /**
     * The JSONHandler instance that is used to collect the {@link Store}s from the server
     */
    private JSONHandlerInter jsonH;

    /**
     * The Default constructor for HomeViewModel
     * Sets up the JSONHandler in order for use if necessary later
     */
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);



    }

    /**
     * Returns the text related to the class that will be displayed
     *
     * @return LiveData<String> object that holds the value of the text for the class
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Checks to see if the list of stores has already been collected and if not calls for them
     * to be collected
     *
     * @return LiveData<ArrayList<Store>> object that holds a live representation of the ArrayList of {@link Store}s
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
     * Calls the JSONHandler to make a request to the server for the list of stores
     */
    private void loadStores(){

        jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);

    }

    /**
     * Takes the server object response and turns the response into a {@link Store} object and
     * adds it to the list of all the stores
     *
     * @param response JSONObject that contatins a store object
     */
    @Override
    public void onSuccess(JSONObject response) {

        Store s = Store.getStore(response);
        Store.addStoreToList(s);

    }

    /**
     * Takes the server JSONArray response and breaks up the array into JSONObjects and send them
     * to the onSucces(JSONObject) method to add it to the complete store list, and then sets the
     * complete store list to the local list
     *
     * @param response JSONArray response from the server that contains {@link Store} objects in JSON form
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

    @Override
    public void onError(VolleyError error) {

    }
}