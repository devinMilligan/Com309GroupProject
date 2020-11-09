package com.example.project309.app;

import java.util.ArrayList;

/**
 * Interface for use by JSONRequestSpec that makes request to the server
 */
public interface JSONRequestInter {

    /**
     * Sets the listener to send the reponses to
     *
     * @param JSONAbstractListener
     */
    void setListener(JSONAbstractListener JSONAbstractListener);

    /**
     *
     *
     * @param url the url to make a request to
     * @param bodyList the body to send with the request
     * @param paramList the params to send with the request
     * @param rm the type of request POST or GET
     */
    void makeJsonObjReqInner(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm);

    /**
     *
     *
     * @param url the url to make a request to
     * @param list the params to send with the request
     */
    void makeJsonArryReqInner(String url, ArrayList<JSONVariable> list);

}
