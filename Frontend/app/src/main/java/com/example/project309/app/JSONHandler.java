package com.example.project309.app;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHandler extends JSONAbstractListener implements JSONHandlerInter {

    JSONRequestInter jR;
    ViewListenerInter vR;

    public JSONHandler(){

        jR = AppController.getInstance().getJSONRequestInstance();

        jR.setListener(this);

    }

    public void setListener(ViewListenerInter view){

        vR = view;

    }

    public void makeJsonObjReq(String url, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, null,  null, rm);
    }

    public void makeJsonObjReqParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, null,  paramList, rm);
    }

    public void makeJsonObjReqBody(String url, ArrayList<JSONVariable> bodyList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, bodyList, null, rm);
    }

    public void makeJsonObjReqCombo(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, bodyList, paramList, rm);
    }

    public void makeJsonArryReq(String url){
        jR.makeJsonArryReqInner(url, null);
    }

    public void makeJsonArryReqParams(String url, ArrayList<JSONVariable> paramList){
        jR.makeJsonArryReqInner(url, paramList);
    }

    @Override
    public void responseListObject(JSONObject response) {
        vR.onSuccess(response);
    }

    @Override
    public void responseListArray(JSONArray response) {
        vR.onSuccess(response);
    }

    @Override
    public void responseListArray(VolleyError error) {
        vR.onError(error);
    }

    @Override
    public void responseListObject(VolleyError error) {
        vR.onError(error);
    }

}
