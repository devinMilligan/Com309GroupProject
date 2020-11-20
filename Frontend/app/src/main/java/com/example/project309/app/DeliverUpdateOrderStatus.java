package com.example.project309.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.java_websocket.client.WebSocketClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeliverUpdateOrderStatus extends AppCompatActivity implements SocketListener, View.OnClickListener, ViewListenerInter{

    public static final String TAG = "UPDATE_STATUS";

    //private WebSocketClient mWebSocketClient;

    private MessageBoxInter message;

    public int success;

    public Button bAdvance;
    public TextView orderNum;
    public TextView status;
    private StringHandlerInter stringHandler;

    /**
     * Runs on the creation of this activity and sets up the structure for the fragments and intializes it
     * then opens up the home fragment
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_confirm_order);

        stringHandler = AppController.getInstance().getStringHandlerInstance();
        stringHandler.setListener(this);

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(DeliverUpdateOrderStatus.this);

        bAdvance = findViewById(R.id.btnSelectOrder);
        bAdvance.setOnClickListener(this);

        orderNum = findViewById(R.id.txtOrderNumberDelivery);
        orderNum.setText("Order Number: " + Order.currentOrder.getOrderNumber());

        status = findViewById(R.id.txtOrderStatusDelivery);
        status.setText("Status: " + Order.currentOrder.getStatus());

        SocketConnect.addListener(this);

        if(SocketConnect.isSocketOpen()) {
            SocketConnect.closeSocket();
        }
        SocketConnect.connect(Const.URL_SOCKET + Order.currentOrder.getUserOrderID());
        //SocketConnect.connect("ws://echo.websocket.org");

        //connectWebSocket();
    }

    @Override
    public void onClick(View v) {
        if(!Order.currentOrder.getStatus().equalsIgnoreCase("Delivered")) {

            ArrayList<JSONVariable> list = new ArrayList<JSONVariable>();
            list.add(new JSONVariable("orderID", Integer.toString(Order.currentOrder.getOrderNumber())));

            stringHandler.makeStringParams(Const.URL_STRING_ADVANCE_STATUS, list, RequestMethod.POST);
        }
        else {
            finish();
        }
    }

    @Override
    public void onMessage(String message) {
        Log.i("Websocket", "Message Received");
    }

    @Override
    public void finish(){

        super.finish();
        SocketConnect.closeSocket();
        SocketConnect.removeListener(this);

    }

    /**
     * Upon a successful login, logs the user in based on their account type
     *
     * @param response JSONObject response from server
     */
    @Override
    public void onSuccess(JSONObject response) {

    }

    @Override
    public void onSuccess(JSONArray response) {

    }

    @Override
    public void onSuccess(String response) {
        Log.d(TAG,response);
        success = 1;
        if(message != null) {
            message.dismissMessage();
        }

        SocketConnect.sendMessage("@" + Order.currentOrder.getUserOrderID() + " " + response);
        //mWebSocketClient.send(response);

        if(response.equalsIgnoreCase("Delivered")) {

            bAdvance.setText("Done");
        }

        Order.currentOrder.setStatus(response);
        status.setText("Status: " + response);
    }

    /**
     * Prints a message box with an error if the login fails
     *
     * @param error Error sent back from request
     */
    @Override
    public void onError(VolleyError error) {
        Log.d(TAG, error.toString());
        success = 0;
        message.dismissMessage();

        message.showMessage("Error: " + error.toString(), 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mWebSocketClient.close();
    }

    /*protected void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://echo.websocket.org");
            //uri = new URI(URL_SOCKET + Profile.currentLogin.getId());
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String message) {
                Log.i("Websocket", "Message Received");
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket", "Error " + ex.getMessage());
            }
        };
        mWebSocketClient.connect();*/
    }

