package com.example.project309.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.project309.net_utils.Const;

import java.util.ArrayList;


public class StringRequestSpec implements StringRequestInter {

    private String TAG = this.getClass().toString();

    // This tag will be used to cancel the request
    private String tag_string_req = "string_req";

    private StringAbstractListener sA;

    /**
     * Constructor
     */
    public StringRequestSpec() {

    }

    /**
     * Makes a string GET request
     * @param urlM the URL to request from
     */
    public void makeStringReqInner(String urlM, ArrayList<JSONVariable> paramList, RequestMethod rm) {

        int method = Request.Method.GET;

        if(rm == RequestMethod.POST){
            method = Request.Method.POST;
        }

        if(paramList != null) {
            for (int i = 0; i < paramList.size(); i++) {  //Creation of link with parameters

                urlM += paramList.get(i).getId() + "=";
                urlM += paramList.get(i).getValue();

                if (i < paramList.size() - 1) {
                    urlM += "&";
                }

            }
        }

        Log.d(TAG, "Made Request");

        //Create String request as GET
        StringRequest strReq = new StringRequest(method, urlM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {  //Handling of response
                Log.d(TAG, response.toString());
                sA.responseListString(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {   //Handling of Error
                VolleyLog.d(TAG, "Error " + error.toString());
                sA.responseListString(error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    @Override
    public void setListener(StringAbstractListener stringAbstractListener) {
        sA = stringAbstractListener;
    }

}