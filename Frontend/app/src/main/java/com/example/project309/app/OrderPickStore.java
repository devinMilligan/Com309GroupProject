package com.example.project309.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This allows the user to pick a store that you want to order from
 *
 * @author Devin Milligan
 */
public class OrderPickStore extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener {

    /**
     * ListView that is used to display the stores
     */
    private ListView lvStores;
    /**
     * List Adapter that helps display the stores on the list view
     */
    private StoreListAdapter sAdapter;
    /**
     * ArrayList that contains the stores that are to be displayed
     */
    private ArrayList<Store> aStores;
    /**
     * {@link JSONHandler} instance used to make requests to the server
     */
    private JSONHandlerInter jsonH;

    /**
     * Runs whe the activity creates and displays the stores on the listview and gets them from the server if they havnt already
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_store);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        aStores = new ArrayList<>();
        lvStores = (ListView) findViewById(R.id.storeLvOrder);

        if(Store.allStores.isEmpty()){
            loadStores();
        }else{
            aStores = Store.allStores;
        }

        sAdapter = new StoreListAdapter(OrderPickStore.this, R.layout.store_list_adapter, aStores);
        lvStores.setAdapter(sAdapter);
        lvStores.setOnItemClickListener(this);

    }

    /**
     * Makes the request to the server for the list of stores
     */
    public void loadStores(){

        jsonH.makeJsonArryReq(Const.URL_JSON_GET_ALL_STORES);

    }

    /**
     * Recieves the response from the server of JSON Object and gets a {@link Store} object from it
     *
     * @param response the JSONObject response that is {@link Store} insatnce in json form
     */
    @Override
    public void onSuccess(JSONObject response) {


        Store s = Store.getStore(response);
        Store.addStoreToList(s);

    }

    /**
     * Gets the JSONArray response from the server and turns this into a list of {@link Store} instances
     * that is set to the current list of stores
     *
     * @param response
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

       aStores = Store.allStores;
        sAdapter.clear();
        sAdapter.addAll(aStores);
        sAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String response) {

    }

    /**
     * Error handling if the request returns an error
     *
     * @param error the error returned by the request
     */
    @Override
    public void onError(VolleyError error) {

        Log.d("ORDERER", error.toString());

    }

    /**
     * Handles a click on a store and sets that to the store to make an order from and send the user
     * to the ordering screen to make an order form that store
     *
     * @param parent
     * @param view
     * @param position the position of the item clicked
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Store.currentStore = sAdapter.getItem(position);
        Intent startOrder = new Intent(OrderPickStore.this, OrderingScreen.class);
        startActivity(startOrder);
        finish();

    }
}