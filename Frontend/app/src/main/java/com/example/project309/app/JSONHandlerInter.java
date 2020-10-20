package com.example.project309.app;

import java.util.ArrayList;

public interface JSONHandlerInter {

    void setListener(ViewListenerInter view);

    void makeJsonObjReq(String url, RequestMethod rm);
    void makeJsonObjReqParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);
    void makeJsonObjReqBody(String url, ArrayList<JSONVariable> bodyList, RequestMethod rm);
    void makeJsonObjReqCombo(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm);
    void makeJsonArryReq(String url);
    void makeJsonArryReqParams(String url, ArrayList<JSONVariable> paramList);

}
