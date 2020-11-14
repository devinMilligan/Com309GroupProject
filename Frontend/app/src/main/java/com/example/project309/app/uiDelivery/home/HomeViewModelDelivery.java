package com.example.project309.app.uiDelivery.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.Order;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class holds the current orders and gets them if necessary and holds the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class HomeViewModelDelivery extends ViewModel implements ViewListenerInter {

    /**
     * Holds the current describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * Holds the local list of all the current orders
     */
    private MutableLiveData<ArrayList<Order>> orders;
    private ArrayList<Order> arrOrders;

    private JSONHandlerInter json;

    /**
     * Default Constructor that sets the current describing text
     */
    public HomeViewModelDelivery() {
        mText = new MutableLiveData<>();
        mText.setValue("Orders");

        json = new JSONHandler();
        json.setListener(this);

        arrOrders = new ArrayList<>();


    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData instance holding the current text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the list of orders has been collected and if not calls to collect them
     *
     * @return LiveData instance of the list of {@link Order}s
     */
    public LiveData<ArrayList<Order>> getOrders(){

        if(orders == null){

            orders = new MutableLiveData<ArrayList<Order>>();

            loadOrders();

        }
        return orders;

    }

    /**
     * Collects the complete list of orders from the server
     */
    private void loadOrders(){

        for(int i = 0; i<Store.allStores.size(); i++) {
            ArrayList<JSONVariable> params = new ArrayList<>();
            params.add(new JSONVariable("store", Integer.toString(Store.allStores.get(i).getID())));

            json.makeJsonArryReqParams(Const.URL_GET_ORDERS_BY_STORE,params);
        }

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