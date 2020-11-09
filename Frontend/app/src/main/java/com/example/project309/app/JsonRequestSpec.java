package com.example.project309.app;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class makes request to the server as JSONArray or JSONObject with and without parameters
 * and body included
 *
 * @author Devin Milligan
 */
public class JsonRequestSpec implements JSONRequestInter{

    /**
     * The TAG to attached to log lines special to this class
     */
    private String TAG = this.getClass().toString();

    /**
     * Tags to used to canel the requests
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    /**
     * The Listener to send the responses to
     */
    private JSONAbstractListener vA;

    /**
     * Default Constructor
     */
    public JsonRequestSpec(){

    }

    public void setListener(JSONAbstractListener JSONAbstractListener){

        vA = JSONAbstractListener;

    }

    //------------------------------------------------------------------------------------------------------------------
    //
    //  Object Requests
    //
    //------------------------------------------------------------------------------------------------------------------

    /**
     * This makes a JSONObject Request to the server with the parameters send to this function
     *
     * @param url the url to make a request to
     * @param bodyList the body to send with the request
     * @param paramList the params to send with the request
     * @param rm the type of request POST or GET
     */
    public void makeJsonObjReqInner(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm) {



        int method = Request.Method.GET;

        if(rm == RequestMethod.POST){
            method = Request.Method.POST;
        }

        if(paramList != null) {
            url += "?";
            for (int i = 0; i < paramList.size(); i++) {  //Creation of link with parameters

                url += paramList.get(i).getId() + "=";
                url += paramList.get(i).getValue();

                if (i < paramList.size() - 1) {
                    url += "&";
                }

            }
        }

        JSONObject body = new JSONObject();

        try {
            if(bodyList == null || bodyList.size() == 0){  //Determine if there is a body and then create object
                body = null;
            }else {
                for (int i = 0; i < bodyList.size(); i++) {
                    body.put(bodyList.get(i).getId(), bodyList.get(i).getValue());
                }
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        //Create JSONObjectRequest to send as POST
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method,
                url, body,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {  //Handling of the response
                        Log.d(TAG, response.toString());
                        vA.responseListObject(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {  //Handling of the error
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                vA.responseListObject(error);
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

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    //------------------------------------------------------------------------------------------------------------------
    //
    //  Array Requests
    //
    //------------------------------------------------------------------------------------------------------------------

    /**
     * This makes a JSONArray Request to the server with the parameters send to this function
     *
     * @param url the url to make a request to
     * @param list the params to send with the request
     */
    public void makeJsonArryReqInner(String url, ArrayList<JSONVariable> list) {

        url += "?";

        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {  //Creation of link with parameters

                url += list.get(i).getId() + "=";
                url += list.get(i).getValue();

                if (i < list.size() - 1) {
                    url += "&";
                }

            }
        }
        //Make JSON array request
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {    //Handling response
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        vA.responseListArray(response);
                    }
                }, new Response.ErrorListener() {   //Handling Error
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                vA.responseListArray(error);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);


        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }



}
