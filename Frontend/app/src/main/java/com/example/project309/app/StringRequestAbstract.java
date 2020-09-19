package com.example.project309.app;

import com.android.volley.VolleyError;

public abstract class StringRequestAbstract {

    protected abstract void responseListString(String response);
    protected abstract void responseListString(VolleyError error);

}
