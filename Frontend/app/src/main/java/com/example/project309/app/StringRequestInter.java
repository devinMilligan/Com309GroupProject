package com.example.project309.app;

import java.util.ArrayList;

/**
 * Interface used by the StringRequestSpec
 *
 * @author Devin Milligan
 */
public interface StringRequestInter {

    /**
     *
     * @param stringAbstractListener the listener to receive the responses
     */
    void setListener(StringAbstractListener stringAbstractListener);


    /**
     *
     * @param url the url to make the request to
     * @param paramList the param list to be sent with the request
     * @param rm the type of request POST or GET
     */
    void makeStringReqInner(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);

}
