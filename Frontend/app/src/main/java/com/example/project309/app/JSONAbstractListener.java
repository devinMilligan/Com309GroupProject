package com.example.project309.app;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface for a JSONListener, but not for activity use
 *
 * @author Devin Milligan
 */
public abstract class JSONAbstractListener {

    /**
     * Descriptor for this class for log messages
     */
    private String TAG ="VOLLEY_ABSTRACT_LISTENER";

    /**
     * Default handling of the response of the request of a JSONObject
     *
     * @param response JSONObbject response from server
     */
    public void responseListObject(JSONObject response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }

    /**
     * Default Handling of the response of the request of a JSONArray
     *
     * @param response JSONArray response from server
     */
    public void responseListArray(JSONArray response){
        Log.d(TAG, "Response Recieved: DO NOTHING");
    }

    /**
     * Default Handling of the response of the request of a VolleyError
     *
     * @param error the error returned from the request
     */
    public void responseListArray(VolleyError error){
        Log.d(TAG, "Response Error: DO NOTHING");
    }

    /**
     * Default Handling of the response of the request of a VolleyError
     *
     * @param error the error returned from the request
     */
    public void responseListObject(VolleyError error){
        Log.d(TAG, "Response Error: DO NOTHING");
    }

}
