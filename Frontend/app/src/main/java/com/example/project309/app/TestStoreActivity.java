package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class TestStoreActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "TEST_STORES";

    private MessageBoxBuilder message;

    private Button AllStoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_store);

        message = new MessageBoxBuilder(com.example.project309.app.TestStoreActivity.this);

        AllStoresButton = findViewById(R.id.all_stores_button);
        AllStoresButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.all_stores_button:
                message.showMessage("Loading...", 3);
                ArrayList<JSONVariable> list = new ArrayList<>();
                jsonRe.makeJsonArryReq(Const.URL_JSON_ALL_STORES, list);
        }

    }

    /**
     * JSON Request Object, used for arrays, bodies, and parms
     */
    JsonRequestSpec jsonRe = new JsonRequestSpec(TAG){

        //Overridden methods to specify how to handle responses and errors
        @Override
        protected void responseListArray(JSONArray response){
            Log.d(TAG,response.toString());
            message.dismissMessage();

            String s;
            for(int i = 0; i < response.length(); i++) {
                try {
                    s = response.get(i).toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent loggedIn = new Intent(com.example.project309.app.TestStoreActivity.this, MainNavigationScreen.class);
                startActivity(loggedIn);
                return;
            }
            message.showMessage("Login Failed", 1);
        }

        @Override
        protected void responseListArray(VolleyError error){
            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);
        }
    };
}

