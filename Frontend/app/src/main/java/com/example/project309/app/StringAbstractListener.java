package com.example.project309.app;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is A class for a defualt listeners for String responses
 *
 * @author Devin Milligan
 */
public abstract class StringAbstractListener {

    /**
     * Tag unique to this class used in log statements
     */
    private String TAG ="VOLLEY_ABSTRACT_LISTENER";

    /**
     * Default: Receives the response of the request
     *
     * @param response the response from the server
     */
    public void responseListString(String response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }

    /**
     * Default: Receives the error of the request
     *
     * @param error the error sent back from the request
     */
    public void responseListString(VolleyError error){ Log.d(TAG, "Response Error: DO NOTHING"); }

}
