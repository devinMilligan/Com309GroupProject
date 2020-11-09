package com.example.project309.app;

import com.android.volley.VolleyError;

import java.util.ArrayList;

/**
 * This class is used to handle requests and response from the server
 *
 * @author Devin Milliga
 *
 */
public class StringHandler extends StringAbstractListener implements StringHandlerInter {

    /**
     * INstance used to direcly make the requests
     */
    StringRequestInter sR;
    /**
     * The listener to get sent the responses
     */
    ViewListenerInter vR;

    /**
     * Default constructor toe set the default listener to this and initialize the String Request Spec
     */
    public StringHandler(){

        sR = AppController.getInstance().getStringRequestInstance();

        sR.setListener(this);

    }

    /**
     * Sets the listener to send the response
     *
     * @param view the listener to send the responses to
     */
    public void setListener(ViewListenerInter view){

        vR = view;

    }

    /**
     * Calls to make a string request from the server
     *
     * @param url the url to send the request to
     * @param rm the type of request to send POST or GET
     */
    @Override
    public void makeStringReq(String url, RequestMethod rm) {
        sR.makeStringReqInner(url,null,rm);
    }

    /**
     * Calls to make a string request using params
     *
     * @param url the url to send the request to
     * @param paramList the param list to send with the request
     * @param rm the type of request to send POST or GET
     */
    @Override
    public void makeStringParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm) {
        sR.makeStringReqInner(url,paramList,rm);
    }

    /**
     * Receives a Strig response from the server
     *
     * @param response the response from the server
     */
    public void responseListString(String response){
        vR.onSuccess(response);
    }

    /**
     * Receives an error from the Server request
     *
     * @param error the error sent back from the request
     */
    public void responseListString(VolleyError error){
        vR.onError(error);
    }

}
