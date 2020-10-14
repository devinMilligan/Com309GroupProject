package com.example.project309.app;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ViewListenerInter {

    public void onSuccess(JSONObject response);
    public void onSuccess(JSONArray response);
    public void onSuccess(String response);
    public void onError(VolleyError error);

}
