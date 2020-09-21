package com.example.project309.app;

import com.android.volley.VolleyError;

public abstract class StringRequestAbstract {

    //Response Handlers Meant to be overridden
    protected abstract void responseListString(String response);
    protected abstract void responseListString(VolleyError error);

}
