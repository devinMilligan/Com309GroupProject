package com.example.project309.app;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface to be used by any class that wants a response from the server requests
 *
 * @author Devin Miligan
 */
public interface ViewListenerInter {

    /**
     *
     * @param response JSONObject response from server
     */
    public void onSuccess(JSONObject response);

    /**
     *
     * @param response JSONArray response from server
     */
    public void onSuccess(JSONArray response);

    /**
     *
     * @param response String response from server
     */
    public void onSuccess(String response);

    /**
     *
     * @param error Error sent back from request
     */
    public void onError(VolleyError error);

}
