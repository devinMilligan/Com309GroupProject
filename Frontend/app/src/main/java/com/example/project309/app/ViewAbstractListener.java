package com.example.project309.app;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ViewAbstractListener extends AppCompatActivity {

    private String TAG ="VIEW_ABSTRACT_LISTENER";

    public void onSuccess(JSONObject response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }
    public void onSuccess(JSONArray response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }
    public void onError(VolleyError error){
        Log.d(TAG, "Response Error: DO NOTHING");
    }

}
