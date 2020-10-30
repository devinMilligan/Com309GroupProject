package com.example.project309.app;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONAbstractListener {

    private String TAG ="VOLLEY_ABSTRACT_LISTENER";

    public void responseListObject(JSONObject response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }
    public void responseListArray(JSONArray response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }

    public void responseListArray(VolleyError error){
        Log.d(TAG, "Response Error: DO NOTHING");
    }
    public void responseListObject(VolleyError error){
        Log.d(TAG, "Response Error: DO NOTHING");
    }

}
