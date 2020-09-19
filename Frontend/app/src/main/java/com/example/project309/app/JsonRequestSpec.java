package com.example.project309.app;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonRequestSpec extends JSONAbstract {

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
    private void makeJsonObjReqParams(ArrayList<JSONVariable> list) {

        final ArrayList<JSONVariable> mList = list;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.URL_JSON_OBJECT, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        responseListParam(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseListParam(error);
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

                for(int i = 0; i<mList.size(); i++) {
                    params.put(mList.get(i).getId(), mList.get(i).getValue());
                }

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

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        responseListArray(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseListArray(error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    public void makeJsonReqBody(ArrayList<JSONVariable> list){

        JSONObject body = new JSONObject();

        try {

            for(int i=0; i<list.size(); i++) {
                body.put(list.get(i).getId(), list.get(i).getValue());
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Const.URL_JSON_BODY, body,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                responseListBody(response);

            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                responseListBody(error);
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


    @Override
    protected void responseListBody(JSONObject response) {
        Log.d(TAG, response.toString() + " DO NOTHING");
    }

    @Override
    protected void responseListArray(JSONArray response) {
        Log.d(TAG, response.toString() + " DO NOTHING");
    }

    @Override
    protected void responseListParam(JSONObject response) {
        Log.d(TAG, response.toString() + " DO NOTHING");
    }

    @Override
    protected void responseListBody(VolleyError error) {
        Log.d(TAG, error.toString() + " ERROR DO NOTHING");
    }

    @Override
    protected void responseListArray(VolleyError error) {
        Log.d(TAG, error.toString() + " ERROR DO NOTHING");
    }

    @Override
    protected void responseListParam(VolleyError error) {
        Log.d(TAG, error.toString() + " ERROR DO NOTHING");
    }
}
