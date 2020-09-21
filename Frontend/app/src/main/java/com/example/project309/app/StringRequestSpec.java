package com.example.project309.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.project309.net_utils.Const;


public class StringRequestSpec extends StringRequestAbstract {

    private String TAG = this.getClass().toString();

    // This tag will be used to cancel the request
    private String tag_string_req = "string_req";


    /**
     * Constructor
     * @param tag this is the TAG from the class the instance was created in for Log calls
     */
    public StringRequestSpec(String tag) {

        TAG = TAG + "," + tag;

    }

    /**
     * Makes a string GET request
     * @param urlM the URL to request from
     */
    public void makeStringReq(String urlM) {


        Log.d(TAG, "Made Request");

        //Create String request as GET
        StringRequest strReq = new StringRequest(Request.Method.GET, urlM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {  //Handling of response
                Log.d(TAG, response.toString());
                responseListString(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {   //Handling of Error
                VolleyLog.d(TAG, "Error " + error.toString());
                responseListString(error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


    //Response and Error Handlers meant to be overridden by instance for more functionality

    @Override
    protected void responseListString(String response) {
        Log.d(TAG, "Dont Go Here");
    }

    @Override
    protected void responseListString(VolleyError error) {

    }
}