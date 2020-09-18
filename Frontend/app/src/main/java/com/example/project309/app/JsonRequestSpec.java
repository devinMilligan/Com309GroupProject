package com.example.project309.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonRequestSpec {

    private String TAG = this.getClass().toString();
    private Context context;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    public JsonRequestSpec(Context mContext){

        context = mContext;
        TAG = TAG + "," + context.getClass().toString();

    }

    /**
     * Making json object request
     * */
    private void makeJsonObjReqParams() {
        final int jsonType = 1;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.URL_JSON_OBJECT, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        responseHandler(response, jsonType);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseHandler(error, jsonType);
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    /**
     * Making json array request
     * */
    private void makeJsonArryReq() {
        final int jsonType = 1;
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        responseHandler(response,jsonType);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseHandler(error,jsonType);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    private void makeJsonReqBody(){

        final int jsonType = 2;
        JSONObject body = new JSONObject();

        try {

            body.put("user", "milldev@iastate.edu:");
            body.put("pass", "password4");
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_JSON_OBJECT, body,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        responseHandler(response,jsonType);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseHandler(error,jsonType);
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);


    }


    private void responseHandler(JSONObject response, int jsonType){

        if(jsonType == 0){

        }
        else if(jsonType == 1){

        }
        else if(jsonType == 2){

        }

        if(context instanceof LogOnTest){
            ((LogOnTest)context).sendResponseJSON(response.toString());
        }



    }

    private void responseHandler(JSONArray response, int jsonType){

        if(jsonType == 0){

        }
        else if(jsonType == 1){

        }
        else if(jsonType == 2){

        }

        if(context instanceof LogOnTest){
            ((LogOnTest)context).sendResponseJSON(response.toString());
        }



    }

    private void responseHandler(VolleyError error, int jsonType){

        if(jsonType == 0){

        }
        else if(jsonType == 1){

        }
        else if(jsonType == 2){

        }

        if(context instanceof LogOnTest){
            ((LogOnTest)context).sendResponseJSON(error.toString());
        }



    }

}
