package com.example.project309.app;

import java.util.ArrayList;

/**
 * Interface Used by the StringHandler
 *
 * @author Devin Milligan
 */
public interface StringHandlerInter {

    /**
     *
     * @param view the listener to send the responses to
     */
    void setListener(ViewListenerInter view);

    /**
     *
     * @param url the url to send the request to
     * @param rm the type of request to send POST or GET
     */
    void makeStringReq(String url, RequestMethod rm);

    /**
     *
     * @param url the url to send the request to
     * @param paramList the param list to send with the request
     * @param rm the type of request to send POST or GET
     */
    void makeStringParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);

}
