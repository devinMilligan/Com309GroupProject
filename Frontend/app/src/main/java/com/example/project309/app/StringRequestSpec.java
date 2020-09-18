package com.example.project309.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.project309.net_utils.Const;

public class StringRequestSpec implements Response.ErrorListener, Response.Listener<String> {

    private String TAG = this.getClass().toString();
    private Context context;

    // This tag will be used to cancel the request
    private String tag_string_req = "string_req";

    public StringRequestSpec(Context mContext) {

        context = mContext;
        TAG = TAG + "," + context.getClass().toString();

    }

    public void makeStringReq(int urlNum) {

        String url = "";

        if(urlNum == 1){
            url = Const.URL_STRING_REQ;
        }

        Log.d(TAG, "Made Request");

        StringRequest strReq = new StringRequest(Request.Method.GET, Const.URL_STRING_REQ,this,this);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        VolleyLog.d(TAG, "Error: " + error.getMessage() + " " + error.networkResponse);
        Log.d(TAG,"Error: " + error.getMessage() + " " + error.toString() );

    }

    @Override
    public void onResponse(String response) {

        Log.d(TAG, response.toString());

        if(context instanceof LogOnTest){
            ((LogOnTest)context).sendResponseString(response.toString());
        }

    }
}