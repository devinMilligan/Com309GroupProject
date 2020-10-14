package com.example.project309.app;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class StringAbstractListener {

    private String TAG ="VOLLEY_ABSTRACT_LISTENER";

    void responseListString(String response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }
    void responseListString(VolleyError error){ Log.d(TAG, "Response Error: DO NOTHING"); }

}
