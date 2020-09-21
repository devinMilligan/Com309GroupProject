package com.example.project309.app;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONAbstract {

    //Methods Meant to be overriden for response handling
    protected abstract void responseListBody(JSONObject response);
    protected abstract void responseListArray(JSONArray response);
    protected abstract void responseListParam(JSONObject response);

    protected abstract void responseListBody(VolleyError error);
    protected abstract void responseListArray(VolleyError error);
    protected abstract void responseListParam(VolleyError error);


}
