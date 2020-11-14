package com.example.project309.app.uiStore.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.Order;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class holds the current orders for the store and the describing text for the fragement
 *
 * @author Devin Milligan
 */
public class HomeViewModelStore extends ViewModel implements ViewListenerInter {

    /**
     * This holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * List of the current orders for the store
     */
    private MutableLiveData<ArrayList<Order>> orders;

    private JSONHandlerInter json;

    private ArrayList<Order> arrOrders = new ArrayList<>();

    /**
     * Default Constructor that sets the describing text for the fragment
     */
    public HomeViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");

        json = new JSONHandler();
        json.setListener(this);

    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData instance contatining the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the orders for this store have been collected and gets them if not and then returns them
     *
     * @return LiveData instance of the {@link Order}s related to this store
     */
    public LiveData<ArrayList<Order>> getOrders(){

        if(orders == null){

            orders = new MutableLiveData<ArrayList<Order>>();

            loadOrders();

        }
        return orders;

    }

    /**
     * Retrieves the orders from the server
     */
    private void loadOrders(){

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("store", Integer.toString(Store.currentStore.getID())));

        json.makeJsonArryReqParams(Const.URL_GET_ORDERS_BY_STORE,params);

    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onSuccess(JSONArray response) {

        try {
            for (int i = 0; i < response.length(); i++) {

                Order temp = Order.getOrderFromJSON(response.getJSONObject(i));
                if(temp.getStatus() != null && temp.getStatus().equalsIgnoreCase("SUBMITTED")){
                    arrOrders.add(temp);
                }

            }
        }catch(JSONException e){}

        orders.setValue(arrOrders);

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }
}