package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This Activity allows for the manager to pick the store of the stores whose information they want to see and edit
 *
 * @author Devin Milligan
 */
public class ManagerPickStore extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener {

    /**
     * List view that will display the {@link Store} instances
     */
    private ListView lvStores;
    /**
     * Adapter to help display the stores on the ListView
     */
    private StoreListAdapter sAdapter;
    /**
     * Array list of all the stores to be displayed
     */
    private ArrayList<Store> aStores;
    /**
     * {@link JSONHandler} instance used to make request to the server
     */
    private JSONHandlerInter jsonH;

    /**
     * Runs when the activity is created and displays the stores on the screens and collects the stores if not gotten already
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_pick_store);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        aStores = new ArrayList<>();
        lvStores = (ListView) findViewById(R.id.storeLvManager);

        if(Store.allStores.isEmpty()){
            loadStores();
        }else{
            aStores = Store.allStores;
        }

        sAdapter = new StoreListAdapter(ManagerPickStore.this, R.layout.store_list_adapter, aStores);
        lvStores.setAdapter(sAdapter);
        lvStores.setOnItemClickListener(this);

    }

    /**
     * Makes a request to the server to get all the manager's stores
     */
    public void loadStores(){

        ArrayList<JSONVariable> paramsList = new ArrayList<>();
        paramsList.add(new JSONVariable("managerID", Integer.toString(Profile.currentLogin.getId())));
        jsonH.makeJsonArryReqParams(Const.URL_JSON_MANAGER_STORES, paramsList);

    }

    /**
     * Takes the response of the server and gets a {@link Store} instance from the JSON object
     *
     * @param response
     */
    @Override
    public void onSuccess(JSONObject response) {


        Store s = Store.getStore(response);
        Store.addStoreToList(s);

    }

    /**
     * Takes the response from the server of JSONArray and creates the list of {@link Store} intances and updates the display
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
     * Error handling of the request error received
     *
     * @param error the error returned from the request
     */
    @Override
    public void onError(VolleyError error) {

        Log.d("MANAGER", error.toString());

    }

    /**
     * Determines what to do on the item click of the listview based on the item clicked
     *
     * @param parent
     * @param view
     * @param position the position of the item clicked
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Store.currentStore = sAdapter.getItem(position);
        Intent startMain = new Intent(ManagerPickStore.this, MainNavigationScreenStore.class);
        startActivity(startMain);

    }
}