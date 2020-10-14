package com.example.project309.app;

import java.util.ArrayList;

public interface StringHandlerInter {

    void setListener(ViewListenerInter view);

    void makeStringReq(String url, RequestMethod rm);
    void makeStringParams(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);

}
