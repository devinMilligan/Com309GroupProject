package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Currency;

public class OrderHistoryCustomer extends AppCompatActivity implements ViewListenerInter, AdapterView.OnItemClickListener {


    ListView lvOrders;
    OrdersListAdapter oAdapter;

    ArrayList<Order> arrOrders = new ArrayList<>();

    JSONHandlerInter json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_customer);

        json = new JSONHandler();
        json.setListener(this);

        lvOrders = (ListView)findViewById(R.id.lvOrderHistory);
        oAdapter = new OrdersListAdapter(OrderHistoryCustomer.this,R.layout.order_list_adapter, arrOrders);
        lvOrders.setAdapter(oAdapter);
        lvOrders.setOnItemClickListener(this);

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("user", Integer.toString(Profile.currentLogin.getId())));

        json.makeJsonArryReqParams(Const.URL_GET_USER_ORDER_HISTORY, params);

    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onSuccess(JSONArray response) {

        oAdapter.clear();
        arrOrders.clear();

        try {
            for (int i = 0; i < response.length(); i++) {

                Order temp = Order.getOrderFromJSON(response.getJSONObject(i));
                if(temp.getStatus() != null && !temp.getStatus().equalsIgnoreCase("Active")){
                    arrOrders.add(temp);
                }

            }
        }catch(JSONException e){}

        oAdapter.addAll(arrOrders);
        oAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

        Log.d("ORDERHISTORYCUST", "Error: " + error.toString());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Order.currentOrder = (Order)parent.getItemAtPosition(position);

        Intent startConf = new Intent(OrderHistoryCustomer.this, OrderConfirmation.class);
        startActivity(startConf);

    }
}