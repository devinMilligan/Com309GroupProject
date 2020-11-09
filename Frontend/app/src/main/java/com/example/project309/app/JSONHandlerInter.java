package com.example.project309.app;

import java.util.ArrayList;

/**
 * Interface used by JSONHandldler to make requests
 *
 * @author Devin Milligan
 */
public interface JSONHandlerInter {

    /**
     * Sets the listener for the request responses to be send to
     *
     * @param view instance to send the responses to
     */
    void setListener(ViewListenerInter view);

    /**
     *
     * @param url the url to make the request to
     * @param rm the type of request POST or GET
     */
    void makeJsonObjReq(String url, RequestMethod rm);

    /**
     *
     * @param url the url to make the request to
     * @param paramList params to be sent with the request
     * @param rm the type of request POST or GET
     */
    void makeJsonObjReqParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);
    /**
     *
     * @param url the url to make the request to
     * @param bodyList the body to be sent with the request
     * @param rm the type of request POST or GET
     */
    void makeJsonObjReqBody(String url, ArrayList<JSONVariable> bodyList, RequestMethod rm);
    /**
     *
     * @param url the url to make the request to
     * @param bodyList the body to be sent with the request
     * @param paramList the params to be sent with the request
     * @param rm the type of request POST or GET
     */
    void makeJsonObjReqCombo(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm);
    /**
     *
     * @param url the url to make the request to
     */
    void makeJsonArryReq(String url);

    /**
     *
     * @param url the url to make the request to
     * @param paramList the params to be sent with the request
     */
    void makeJsonArryReqParams(String url, ArrayList<JSONVariable> paramList);

}
