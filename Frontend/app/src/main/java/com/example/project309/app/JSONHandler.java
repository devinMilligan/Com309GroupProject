package com.example.project309.app;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class allows for the handling of the response and requests to and from the server for abstraction
 *
 * @author Devin Milligan
 */
public class JSONHandler extends JSONAbstractListener implements JSONHandlerInter {

    /**
     * {@link JsonRequestSpec} instance used to actually make the requests
     */
    JSONRequestInter jR;
    /**
     * Instance used to send a response to
     */
    ViewListenerInter vR;

    /**
     * Default constructor to set the default listener
     */
    public JSONHandler(){

        jR = AppController.getInstance().getJSONRequestInstance();

        jR.setListener(this);

    }

    /**
     * Sets the listener for the request responses to be send to
     *
     * @param view instance to send the responses to
     */
    public void setListener(ViewListenerInter view){

        vR = view;

    }

    /**
     * Calls to make a JSON Request with no Params and no Body
     *
     * @param url the url to make the request to
     * @param rm the type of request POST or GET
     */
    public void makeJsonObjReq(String url, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, null,  null, rm);
    }

    /**
     * Calls to make a JSON Request with Parameters and No Body
     *
     * @param url the url to make the request to
     * @param paramList params to be sent with the request
     * @param rm the type of request POST or GET
     */
    public void makeJsonObjReqParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, null,  paramList, rm);
    }

    /**
     * Calls to make a JSON Request with Body and no Parameters
     *
     * @param url the url to make the request to
     * @param bodyList the body to be sent with the request
     * @param rm the type of request POST or GET
     */
    public void makeJsonObjReqBody(String url, ArrayList<JSONVariable> bodyList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, bodyList, null, rm);
    }

    /**
     * Calls to make a JSON Request with with Body and Parameters
     *
     * @param url the url to make the request to
     * @param bodyList the body to be sent with the request
     * @param paramList the params to be sent with the request
     * @param rm the type of request POST or GET
     */
    public void makeJsonObjReqCombo(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        jR.makeJsonObjReqInner(url, bodyList, paramList, rm);
    }

    /**
     * Make a JSON Array Request with no params
     *
     * @param url the url to make the request to
     */
    public void makeJsonArryReq(String url){
        jR.makeJsonArryReqInner(url, null);
    }

    /**
     * Calls to make JSONArray Request with params
     *
     * @param url the url to make the request to
     * @param paramList the params to be sent with the request
     */
    public void makeJsonArryReqParams(String url, ArrayList<JSONVariable> paramList){
        jR.makeJsonArryReqInner(url, paramList);
    }

    /**
     * Gets the reponse and sends it to the listener
     *
     * @param response JSONObbject response from server
     */
    @Override
    public void responseListObject(JSONObject response) {
        vR.onSuccess(response);
    }

    /**
     * Gets the reponse and sends it to the listener
     *
     * @param response JSONArray response from server
     */
    @Override
    public void responseListArray(JSONArray response) {
        vR.onSuccess(response);
    }

    /**
     * Gets the reponse and sends it to the listener
     *
     * @param error the error returned from the request
     */
    @Override
    public void responseListArray(VolleyError error) {
        vR.onError(error);
    }

    /**
     * Gets the reponse and sends it to the listener
     *
     * @param error the error returned from the request
     */
    @Override
    public void responseListObject(VolleyError error) {
        vR.onError(error);
    }

}
