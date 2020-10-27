package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ManagerPickStore extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener {

    private ListView lvStores;
    private StoreListAdapter sAdapter;
    private ArrayList<Store> aStores;
    private JSONHandlerInter jsonH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_pick_store);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        aStores = new ArrayList<>();
        lvStores = (ListView) findViewById(R.id.lvStores);

        if(Store.allStores == null){
            loadStores();
        }else{
            aStores = Store.allStores;
        }

        sAdapter = new StoreListAdapter(ManagerPickStore.this, R.layout.store_list_adapter, aStores);
        lvStores.setAdapter(sAdapter);
        lvStores.setOnItemClickListener(this);

    }

    public void loadStores(){

        ArrayList<JSONVariable> paramsList = new ArrayList<>();
        paramsList.add(new JSONVariable("managerID", Integer.toString(Profile.currentLogin.getId())));
        jsonH.makeJsonArryReqParams(Const.URL_JSON_MANAGER_STORES, paramsList);

    }

    @Override
    public void onSuccess(JSONObject response) {


        Store s = Store.getStore(response);
        Store.addStoreToList(s);

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

       aStores = Store.allStores;

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Store.currentStore = sAdapter.getItem(position);
        Intent startMain = new Intent(ManagerPickStore.this, MainNavigationScreenStore.class);
        startActivity(startMain);

    }
}