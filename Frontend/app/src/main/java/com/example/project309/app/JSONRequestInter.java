package com.example.project309.app;

import java.util.ArrayList;

public interface JSONRequestInter {

    void setListener(JSONAbstractListener JSONAbstractListener);

    void makeJsonObjReqInner(String url, ArrayList<JSONVariable> bodyList, ArrayList<JSONVariable> paramList, RequestMethod rm);
    void makeJsonArryReqInner(String url, ArrayList<JSONVariable> list, RequestMethod rm);

}
