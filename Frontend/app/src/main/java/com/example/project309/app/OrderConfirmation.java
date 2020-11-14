package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderConfirmation extends AppCompatActivity implements SocketListener, ViewListenerInter {

    Order currentOrder;

    TextView txtStatus, txtOrderNum;

    JSONHandlerInter json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        currentOrder = Order.currentOrder;

        txtStatus = (TextView)findViewById(R.id.txtStatus);
        txtOrderNum = (TextView)findViewById(R.id.txtOrderNumberConf);

        txtOrderNum.setText("Order Number: " + currentOrder.getOrderNumber());

        txtStatus.setText("Status: " + currentOrder.getStatus());

        if(!currentOrder.getStatus().equalsIgnoreCase("delivered")) {
            json = new JSONHandler();
            json.setListener(this);

            SocketConnect.addListener(this);

            if(SocketConnect.isSocketOpen()) {
                SocketConnect.closeSocket();
            }
            SocketConnect.connect(Const.URL_SOCKET);
        }


    }

    @Override
    public void onMessage(String message) {

        ArrayList<JSONVariable> params = new ArrayList<>();
        params.add(new JSONVariable("id", Integer.toString(currentOrder.getOrderNumber())));

        json.makeJsonObjReqParams(Const.URL_CHECK_ORDER_STATUS,params, RequestMethod.GET);

    }

    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onSuccess(JSONArray response) {

    }

    @Override
    public void onSuccess(String response) {

        currentOrder.setStatus(response);

        txtStatus.setText("Status: " + currentOrder.getStatus());

    }

    @Override
    public void finish(){

        super.finish();
        SocketConnect.closeSocket();
        SocketConnect.removeListener(this);

    }

    @Override
    public void onError(VolleyError error) {

    }
}