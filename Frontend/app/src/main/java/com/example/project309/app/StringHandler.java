package com.example.project309.app;

import com.android.volley.VolleyError;

import java.util.ArrayList;

public class StringHandler extends StringAbstractListener implements StringHandlerInter {

    StringRequestInter sR;
    ViewListenerInter vR;

    public StringHandler(){

        sR = AppController.getInstance().getStringRequestInstance();

        sR.setListener(this);

    }

    public void setListener(ViewListenerInter view){

        vR = view;

    }

    @Override
    public void makeStringReq(String url, RequestMethod rm) {
        sR.makeStringReqInner(url,null,rm);
    }

    @Override
    public void makeStringParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        sR.makeStringReqInner(url,paramList,rm);
    }

    void responseListString(String response){
        vR.onSuccess(response);
    }
    void responseListString(VolleyError error){
        vR.onError(error);
    }

}
